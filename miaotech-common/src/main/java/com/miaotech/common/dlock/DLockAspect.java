package com.miaotech.common.dlock;

import com.miaotech.common.MsgException;
import com.miaotech.common.result.ResultEnum;
import com.miaotech.common.utils.AspectUtils;
import com.miaotech.common.aspect.AopKeyGenerator;
import com.miaotech.common.aspect.AopKeyResolver;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.redis.util.RedisLockRegistry;
import org.springframework.util.StringUtils;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

/**
 * The Idempotent Aspect
 *
 */
@Slf4j
@Aspect
public class DLockAspect {

    /**
     * redis cache key template
     */
    private static final String KEY_TEMPLATE = "dlock:%s:%s";

    @Autowired
    private RedisLockRegistry redisLockRegistry;

    @Autowired
    private AopKeyResolver aopKeyResolver;

    @Autowired
    private AopKeyGenerator aopKeyGenerator;

    @Pointcut("@annotation(com.miaotech.common.dlock.DLock)")
    public void pointCut() {
    }

    @Around("pointCut()")
    public void aroundPointCut(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        if (!method.isAnnotationPresent(DLock.class)) {
            return;
        }
        method = AspectUtils.getMethod(joinPoint);

        DLock dlock = method.getAnnotation(DLock.class);

        String group = dlock.group();
        String key;

        if (!StringUtils.hasLength(dlock.key())) {
            // 若没有配置标识编号，则使用 类名 + 方法名 + 参数列表作为区分
            key = String.format(KEY_TEMPLATE, group, aopKeyGenerator.generate(method, joinPoint.getArgs()));
        }
        else {
            // 使用jstl 规则区分
            key = String.format(KEY_TEMPLATE, group, aopKeyResolver.resolver(dlock.key(), method, joinPoint.getArgs()));
        }

        long timeout = dlock.timeout();

        Lock lock = redisLockRegistry.obtain(key);
        try{
            //尝试在指定时间内加锁，如果已经有其他锁锁住，获取当前线程不能加锁，则返回false，加锁失败；加锁成功则返回true
            if(lock.tryLock(timeout, TimeUnit.SECONDS)){
                log.info("lock is ready");
                joinPoint.proceed();
            }
        } catch (InterruptedException e) {
            log.error("obtain lock error",e);
            throw MsgException.newMessageException(ResultEnum.DLOCK_ERROR.getCode(), "请求DLock异常，key=" + key);
        } finally {
            lock.unlock();
        }
    }

}
