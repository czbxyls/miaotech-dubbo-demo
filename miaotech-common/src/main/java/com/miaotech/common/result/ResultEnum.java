package com.miaotech.common.result;

public enum ResultEnum {
    UNKNOWN_ERROR(-1, "o(╥﹏╥)o~~系统出异常啦!,请联系管理员!!!"),
    INTERNAL_ERROR(501, "o(╥﹏╥)o~~请求服务内部异常!!!"),
    TIMEOUT_ERROR(502, "o(╥﹏╥)o~~请求服务超时!!!"),
    VALIDATOR_ERROR(502, "o(╥﹏╥)o~~请求参数校验未通过!!!"),

    LIMIT_ERROR(550, "o(╥﹏╥)o~~当前请求被限制访问!!!"),
    REPEAT_ERROR(551, "o(╥﹏╥)o~~当前请求重复，请稍后重试!!!"),
    DLOCK_ERROR(552, "o(╥﹏╥)o~~尝试请求分布式锁失败!!!"),

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
