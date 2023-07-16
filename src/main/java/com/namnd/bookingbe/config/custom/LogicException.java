package com.namnd.bookingbe.config.custom;


import com.namnd.bookingbe.Enum.ErrorCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LogicException extends RuntimeException {

    private String code;
    private String message;

    private  ErrorCode errorCode;

    public LogicException(String code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

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
