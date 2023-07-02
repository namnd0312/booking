package com.namnd.bookingbe.config.custom;


import com.namnd.bookingbe.Enum.ErrorCode;


public class LogicException extends RuntimeException {

    private final ErrorCode errorCode;

    public LogicException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return errorCode.getCode();
    }

    public String getErrorMessage() {
        return errorCode.getMessage();
    }
}
