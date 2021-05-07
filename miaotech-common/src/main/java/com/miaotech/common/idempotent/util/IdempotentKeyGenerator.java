package com.miaotech.common.idempotent.util;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;

import java.lang.reflect.Method;

@Slf4j
public class IdempotentKeyGenerator {

    /**
     * Generate key based on {method name + parameter list} and md5 conversion
     */
    public String generate(Class klass, Method method, Object... args) {
        String prefix = klass.toString() + "." + method.toString();
        StringBuilder sb = new StringBuilder(prefix);
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
