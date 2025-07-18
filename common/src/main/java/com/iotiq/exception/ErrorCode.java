package com.iotiq.exception;

import lombok.Getter;

@Getter
public enum ErrorCode {
    USER_ALREADY_EXISTS(1000, "USER_ALREADY_EXISTS"),
    PASSWORD_NOT_MATCH(1001, "PASSWORD_NOT_MATCH"),
    USER_NOT_FOUND(1002, "USER_NOT_FOUND"),
    INVALID_CREDENTIALS(1003, "INVALID_CREDENTIALS"),
    TOKEN_NOT_FOUND(1004, "TOKEN_NOT_FOUND"),
    SERVICE_UNAVAILABLE(1005, "SERVICE_UNAVAILABLE"),
    CHAT_NOT_FOUND(1006, "CHAT_NOT_FOUND");

    private final int code;
    private final String messagePrefix;

    ErrorCode(int code, String messagePrefix) {
        this.code = code;
        this.messagePrefix = messagePrefix;
    }

}
