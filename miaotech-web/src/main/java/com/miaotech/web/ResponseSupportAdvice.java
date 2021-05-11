package com.miaotech.web;

import com.alibaba.fastjson.JSON;
import com.miaotech.common.result.ApiResult;
import com.miaotech.common.result.ApiResultUtil;
import com.miaotech.common.utils.CommonUtils;
import com.miaotech.web.common.WebProperties;
import com.sun.tools.javac.util.Convert;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

;import javax.annotation.Resource;
import java.util.Map;

@Slf4j
@RestControllerAdvice
@Order(value=Ordered.LOWEST_PRECEDENCE)
public class ResponseSupportAdvice implements ResponseBodyAdvice {

    @Autowired
    WebProperties config;

    @Override
    public boolean supports(MethodParameter methodParameter, Class aClass) {
        //log.info("rest.response.wrapper={}", config.isRespWrapper());
        return config.isRespWrapper();
    }

    @Override
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter,
                                  MediaType mediaType, Class aClass,
                                  ServerHttpRequest serverHttpRequest,
                                  ServerHttpResponse serverHttpResponse) {
        log.info("return value: {}", JSON.toJSONString(o));
        if(o != null) { //o是否是文件
            if(o instanceof ApiResult || o instanceof Resource) {
                return o;
            }
        }
        //该方法返回的媒体类型是否是application/json。若不是，直接返回响应内容
        if (!mediaType.includes(MediaType.APPLICATION_JSON)) {
            return o;
        }
        //处理404的问题: 建议还是覆盖BasicErrorController。其他方式更加不优雅
        //https://www.cnblogs.com/54chensongxia/p/14007696.html
        return ApiResultUtil.success(o);
    }

    public static void main(String[] args) {
        String str = "";
        System.out.println(str instanceof String);
    }
}
