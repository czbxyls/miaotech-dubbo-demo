package com.miaotech.dubbo.common;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.common.extension.Activate;
import org.apache.dubbo.rpc.*;

@Slf4j
@Activate
public class ServerFilter implements Filter {
    @Override
    public Result invoke(Invoker<?> invoker, Invocation inv) throws RpcException {
        Result result = invoker.invoke(inv);
        //just for test：这里只是为了测试自定义filter
        log.info("client ip={}", RpcContext.getContext().getRemoteHost());
        return result;
    }
}