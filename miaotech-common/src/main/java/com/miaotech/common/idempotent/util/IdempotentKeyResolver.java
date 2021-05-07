package com.miaotech.common.idempotent.util;

import com.miaotech.common.idempotent.Idempotent;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.expression.Expression;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import java.lang.reflect.Method;

public class IdempotentKeyResolver {
    private static final SpelExpressionParser PARSER = new SpelExpressionParser();

    private static final LocalVariableTableParameterNameDiscoverer DISCOVERER = new LocalVariableTableParameterNameDiscoverer();

    public String resolver(Idempotent idempotent, JoinPoint point) {
        Object[] arguments = point.getArgs();
        String[] params = DISCOVERER.getParameterNames(getMethod(point));
        StandardEvaluationContext context = new StandardEvaluationContext();

        if (params != null && params.length > 0) {
            for (int len = 0; len < params.length; len++) {
                context.setVariable(params[len], arguments[len]);
            }
        }

        Expression expression = PARSER.parseExpression(idempotent.key());
        return expression.getValue(context, String.class);
    }

    /**
     * 根据切点解析方法信息
     * @param joinPoint 切点信息
     * @return Method 原信息
     */
    private Method getMethod(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        if (method.getDeclaringClass().isInterface()) {
            try {
                method = joinPoint.getTarget().getClass().getDeclaredMethod(joinPoint.getSignature().getName(),
                        method.getParameterTypes());
            }
            catch (SecurityException | NoSuchMethodException e) {
                throw new RuntimeException(e);
            }
        }
        return method;
    }
}
