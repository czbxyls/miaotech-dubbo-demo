package com.miaotech.common;

import com.miaotech.common.result.ResultEnum;

public final class MsgException extends RuntimeException {

    private static final long serialVersionUID = -102324565904130269L;

    private int code;

    public MsgException(String message) {
        super(message);
        this.code = ResultEnum.ERROR.getCode();
    }

    public MsgException(int code, String message) {
        super(message);
        this.code = code;
    }

    public MsgException(int code, String message, Throwable throwable) {
        super(message, throwable);
        this.code = code;
    }

    public MsgException(Throwable cause) {
        super(cause);
        this.code = ResultEnum.ERROR.getCode();
    }

    public MsgException(ResultEnum resultEnum) {
        this(resultEnum.getCode(), resultEnum.getMsg());
    }

    public int getCode() {
        return this.code;
    }

    public MsgException setCode(int code) {
        this.code = code;
        return this;
    }

    public static MsgException newMessageException(String message) {
        return newMessageException(ResultEnum.ERROR.getCode(), message);
    }

    public static final MsgException newMessageException(int code, String message) {
        return new MsgException(code, message);
    }

    public static final MsgException newMessageException(ResultEnum resultEnum) {
        return new MsgException(resultEnum);
    }

    public static final MsgException newMessageException(Exception e) {
        return new MsgException(e);
    }
}
