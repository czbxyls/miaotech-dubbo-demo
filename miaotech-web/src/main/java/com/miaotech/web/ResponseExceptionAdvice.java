package com.miaotech.web;


import com.miaotech.common.MsgException;
import com.miaotech.common.result.ApiResultUtil;
import com.miaotech.common.result.ResultEnum;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.remoting.TimeoutException;
import org.apache.dubbo.rpc.RpcException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ResponseExceptionAdvice {

    /**
     * 统一异常处理
     *
     * @param e Exception
     */
    @ExceptionHandler()
    public Object exceptionHandler(Exception e) {
        if (e instanceof MsgException) {
            //全局基类自定义异常,返回{code,msg}
            MsgException baseException = (MsgException) e;
            return ApiResultUtil.error(baseException);
        } else if(e instanceof RpcException) {
            log.error("请求服务超时异常: ", e);
            return ApiResultUtil.error(ResultEnum.INTERNAL_ERROR);
        } else {
            log.error("系统未知异常: ", e);
            return ApiResultUtil.error(ResultEnum.UNKNOWN_ERROR);
        }
    }

}