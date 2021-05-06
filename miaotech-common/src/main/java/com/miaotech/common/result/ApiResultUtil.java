package com.miaotech.common.result;

import com.miaotech.common.MsgException;

public class ApiResultUtil {
    /**
     * 访问成功时调用 包含data
     * @param object
     * @return
     */
    public static ApiResult success(Object object){
        return new ApiResult().success(object);
    }

    /**
     * 访问成功时调用 不包含data
     * @return
     */
    public static ApiResult success(){
        return success(null);
    }

    /**
     * 返回异常情况 不包含data
     * @param code
     * @param msg
     * @return
     */
    public static ApiResult error(Integer code, String msg){
        return new ApiResult().error(code, msg);
    }

    public static ApiResult error(String msg){
        return new ApiResult().error(msg);
    }

    /**
     * 全局基类自定义异常 异常处理
     * @param e
     * @return
     */
    public static ApiResult error(MsgException e){
        return error(e.getCode(),e.getMessage());
    }

    /**
     * 返回异常情况 不包含data
     * @param resultEnum 结果枚举类　统一管理　code msg
     * @return
     */
    public static ApiResult error(ResultEnum resultEnum){
        return error(resultEnum.getCode(),resultEnum.getMsg());
    }
}
