package com.miaotech.common.result;

public enum ResultEnum {
    UNKNOWN_ERROR(-1, "o(╥﹏╥)o~~系统出异常啦!,请联系管理员!!!"),
    INTERNAL_ERROR(101, "o(╥﹏╥)o~~系统RPC内部异常!!!"),
    LIMIT_ERROR(102, "o(╥﹏╥)o~~当前请求被限流了!!!"),
    REPEAT_ERROR(103, "o(╥﹏╥)o~~当前请求重复了!!!"),
    SUCCESS(200, "success"),
    ERROR(500, "error"),
    WARN(300, "warn");

    private Integer code;

    private String msg;

    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
