package com.miaotech.common.result;

import lombok.Data;
import lombok.Getter;

import java.io.Serializable;


@Data
public class ApiResult<T> implements Serializable {
    private static final long serialVersionUID = 4563841876874219154L;

    @Getter
    protected int code = 200;

    @Getter
    protected T result;

    @Getter
    protected String message;

    public ApiResult<T> error(String message) {
        return error(ResultEnum.ERROR.getCode(), message);
    }

    public ApiResult<T> error(int code, String message) {
        this.code = code;
        this.message = message;
        return this;
    }

    public ApiResult<T> success(T t) {
        this.result = t;
        this.code = ResultEnum.SUCCESS.getCode();
        return this;
    }

    public ApiResult<T> warn(String message) {
        return warn(null, message);
    }

    public ApiResult<T> warn(T t, String message) {
        this.result = t;
        this.code = ResultEnum.WARN.getCode();
        this.message = message;
        return this;
    }

    //@JsonIgnoreType
    public boolean isSuccess() {
        return (this.code == ResultEnum.SUCCESS.getCode());
    }

    //@JsonIgnoreType
    public boolean isWarn() {
        return (this.code == ResultEnum.WARN.getCode()  );
    }

    //@JsonIgnoreType
    public boolean isError() {
        return !isSuccess() && !isWarn();
    }

    public String toString() {
        return "ApiResult [code=" + this.code + ", result=" + this.result + ", message=" + this.message + "]";
    }

}