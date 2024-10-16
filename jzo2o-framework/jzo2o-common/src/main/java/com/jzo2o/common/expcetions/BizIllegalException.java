package com.jzo2o.common.expcetions;

public class BizIllegalException extends CommonException{
    public BizIllegalException(String message) {
        super(message);
    }

    public BizIllegalException(int code, String message) {
        super(code, message);
    }

    public BizIllegalException(int code, String message, Throwable cause) {
        super(cause, code, message);
    }
}
