package com.namnd.bookingbe.Enum;

public enum ErrorCode {
    INVALID_INPUT("1001", "Invalid input"),
    NOT_FOUND("1002", "Not found"),
    INTERNAL_ERROR("1003", "Internal server error"),

    AUTH_ERROR("401", "Auth error: %s");

    private final String code;
    private final String message;

    ErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
