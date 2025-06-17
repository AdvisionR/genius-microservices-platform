package com.iotiq.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public abstract class ApplicationException extends RuntimeException{
    private final HttpStatus status;
    private final int code;
    private final String messagePrefix;
    private final String message;

    public ApplicationException(HttpStatus status, ErrorCode errorCode, String message) {
        super(message);
        this.status = status;
        this.code = errorCode.getCode();
        this.messagePrefix = errorCode.getMessagePrefix();
        this.message = message;
    }
}
