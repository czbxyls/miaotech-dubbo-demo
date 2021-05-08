package com.miaotech.web;


import com.miaotech.common.MsgException;
import com.miaotech.common.result.ApiResultUtil;
import com.miaotech.common.result.ResultEnum;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.remoting.TimeoutException;
import org.apache.dubbo.rpc.RpcException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
public class ResponseExceptionAdvice {

    @ExceptionHandler({MsgException.class})
    public Object exceptionHandler(MsgException e) {
        log.info("处理正常业务异常！");
        return ApiResultUtil.error(e);
    }

    @ExceptionHandler({BindException.class})
    public Object exceptionHandler(BindException e) {
        log.error("请求参数异常: ", e);
        List<String> violations = e.
                getBindingResult().getFieldErrors().stream().
                map(FieldError::getDefaultMessage).
                collect(Collectors.toList());
        return ApiResultUtil.error(ResultEnum.VALIDATOR_ERROR, violations);
    }

    /**
     * 统一异常处理
     *
     * @param e Exception
     */
    @ExceptionHandler()
    public Object exceptionHandler(Exception e) {
        if(e instanceof RpcException) {
            return ApiResultUtil.error(ResultEnum.INTERNAL_ERROR);
        } else {
            log.error("系统未知异常: ", e);
            return ApiResultUtil.error(ResultEnum.UNKNOWN_ERROR);
        }
    }

}