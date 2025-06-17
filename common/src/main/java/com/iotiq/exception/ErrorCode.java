package com.iotiq.exception;

import lombok.Getter;

@Getter
public enum ErrorCode {
    USER_ALREADY_EXISTS(1000, "USER_ALREADY_EXISTS"),
    PASSWORD_NOT_MATCH(1001, "PASSWORD_NOT_MATCH");

    private final int code;
    private final String messagePrefix;

    ErrorCode(int code, String messagePrefix) {
        this.code = code;
        this.messagePrefix = messagePrefix;
    }

}
