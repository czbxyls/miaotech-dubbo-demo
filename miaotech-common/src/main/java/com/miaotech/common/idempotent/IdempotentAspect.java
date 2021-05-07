package com.miaotech.common.idempotent;

import com.miaotech.common.cache.RedisUtil;
import com.miaotech.common.idempotent.util.IdempotentKeyResolver;
import com.miaotech.common.idempotent.util.IdempotentKeyGenerator;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * The Idempotent Aspect
 *
 */
@Slf4j
@Aspect
public class IdempotentAspect {

    private static final ThreadLocal<Map<String, Object>> THREAD_CACHE = ThreadLocal.withInitial(HashMap::new);

    /**
     * redis cache key template
     */
    private static final String KEY_TEMPLATE = "idempotent_%s";

    private static final String KEY = "key";

    private static final String ISAUTODEL = "isAutoDel";

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private IdempotentKeyResolver idempotentKeyResolver;

    @Autowired
    private IdempotentKeyGenerator idempotentKeyGenerator;

    @Pointcut("@annotation(com.miaotech.common.idempotent.Idempotent)")
    public void pointCut() {
    }

    @Before("pointCut()")
    public void beforePointCut(JoinPoint joinPoint) throws Exception {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        if (!method.isAnnotationPresent(Idempotent.class)) {
            return;
        }
        Idempotent idempotent = method.getAnnotation(Idempotent.class);

        String key;
        Class klass = signature.getClass();
        // 若没有配置 幂等 标识编号，则使用 类名 + 方法名 + 参数列表作为区分
        if (!StringUtils.hasLength(idempotent.key())) {
            key = String.format(KEY_TEMPLATE, idempotentKeyGenerator.generate(klass, method, joinPoint.getArgs()));
        }
        else {
            // 使用jstl 规则区分
            key = String.format(KEY_TEMPLATE, idempotentKeyResolver.resolver(idempotent, joinPoint));
        }

        long ttl = idempotent.ttl();
        String info = idempotent.info();
        boolean isAutoDel = idempotent.isAutoDel();

        // do not need check null
        String value = LocalDateTime.now().toString().replace("T", " ");
        Boolean flag = redisUtil.setNx(key, value, ttl);

        if(!flag) {
            throw new IdempotentException("[idempotent]:" + info);
        }

        Map<String, Object> map = THREAD_CACHE.get();
        map.put(KEY, key);
        map.put(ISAUTODEL, isAutoDel);
    }

    @After("pointCut()")
    public void afterPointCut(JoinPoint joinPoint) {
        Map<String, Object> map = THREAD_CACHE.get();
        if (CollectionUtils.isEmpty(map)) {
            return;
        }

        String key = map.get(KEY).toString();
        boolean isAutoDel = (boolean) map.get(ISAUTODEL);

        if (isAutoDel) {
            redisUtil.del(key);
            log.info("[idempotent]:has removed key={}", key);
        }
        THREAD_CACHE.remove();
    }

}
