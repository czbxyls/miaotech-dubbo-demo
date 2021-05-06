package com.miaotech.dubbo.common;

import com.alibaba.csp.sentinel.adapter.dubbo.config.DubboAdapterGlobalConfig;
import com.alibaba.csp.sentinel.adapter.dubbo.fallback.DubboFallback;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.miaotech.common.MsgException;
import com.miaotech.common.result.ResultEnum;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.rpc.AsyncRpcResult;
import org.apache.dubbo.rpc.Invocation;
import org.apache.dubbo.rpc.Invoker;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.apache.dubbo.rpc.AsyncRpcResult;
import org.apache.dubbo.rpc.Invocation;
import org.apache.dubbo.rpc.Invoker;
import org.apache.dubbo.rpc.Result;

@Slf4j
@Configuration
public class ServerContainer {
    //is it thread safe?
    @PostConstruct
    public void init(){
        log.info("Server initializer begin......");
        System.out.println("hello! I'm initialize something!");
        DubboAdapterGlobalConfig.setProviderFallback((invoker, invocation, ex) -> {
            log.info("捕获到block异常，降级处理", ex);
            return AsyncRpcResult.newDefaultAsyncResult(MsgException.newMessageException(ResultEnum.LIMIT_ERROR), invocation);
        });
        log.info("Server initializer end......");
    }

    @PreDestroy
    public void destroy(){
        log.info("Shutdown process initiated...");
        System.out.println("hello! I'm release something!");
        log.info("Graceful shutdown is processed successfully");
    }
}
