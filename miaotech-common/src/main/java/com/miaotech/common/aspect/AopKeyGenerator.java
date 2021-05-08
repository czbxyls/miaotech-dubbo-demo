package com.miaotech.common.aspect;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Method;

@Slf4j
@Configuration
public class AopKeyGenerator {

    /**
     * Generate key based on {method name + parameter list} and md5 conversion
     */
    public String generate(Method method, Object... args) {
        String prefix = method.getDeclaringClass().getSimpleName() + "." + method.getName();
        StringBuilder sb = new StringBuilder(method.toString());
        for (Object arg : args) {
            sb.append(toString(arg));
        }
        return prefix + ":" + DigestUtils.md5Hex(sb.toString());
    }

    private String toString(Object object) {
        if (object == null) {
            return "null";
        }
        if (object instanceof Number) {
            return object.toString();
        }
        // Call json tool class to convert to String
        return toJson(object);
    }

    /**
     * Java Object Maps To Json
     */
    private String toJson(Object obj) {
        String result;
        if (obj == null || obj instanceof String) {
            return (String) obj;
        }
        try {
            result = JSON.toJSONString(obj);
        } catch (Exception e) {
            log.error("Java Object Maps To Json Error !");
            throw new RuntimeException("Java Object Maps To Json Error !", e);
        }
        return result;
    }
}
