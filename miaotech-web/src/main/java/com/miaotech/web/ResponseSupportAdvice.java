package com.miaotech.web;

import com.alibaba.fastjson.JSON;
import com.miaotech.common.result.ApiResult;
import com.miaotech.common.result.ApiResultUtil;
import com.miaotech.web.common.WebConfig;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

;

@Slf4j
@RestControllerAdvice
public class ResponseSupportAdvice implements ResponseBodyAdvice {

    @Autowired
    WebConfig config;

    @Override
    public boolean supports(MethodParameter methodParameter, Class aClass) {
        log.info("rest.response.wrapper={}", config.isRespWrapper());
        return config.isRespWrapper();
    }

    @Override
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter,
                                  MediaType mediaType, Class aClass,
                                  ServerHttpRequest serverHttpRequest,
                                  ServerHttpResponse serverHttpResponse) {
        log.info("return value: {}", JSON.toJSONString(o));
        if(o != null && o instanceof ApiResult) return o;
        return ApiResultUtil.success(o);
    }
}
