package com.miaotech.common.idempotent;

import com.miaotech.common.MsgException;
import com.miaotech.common.result.ResultEnum;

public class IdempotentException extends MsgException {

    public IdempotentException(String message) {
        super(ResultEnum.REPEAT_ERROR.getCode(), message);
    }

    public IdempotentException(String message, Throwable cause) {
        super(ResultEnum.REPEAT_ERROR.getCode(), message, cause);
    }

    public IdempotentException(Throwable cause) {
        super(ResultEnum.REPEAT_ERROR.getCode(), ResultEnum.REPEAT_ERROR.getMsg(), cause);
    }

}
