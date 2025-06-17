package com.iotiq.exception;

import com.iotiq.message.ResponseMessages;
import org.springframework.http.HttpStatus;

public class InvalidCredentialsException extends ApplicationException {
    public InvalidCredentialsException() {
        super(HttpStatus.UNAUTHORIZED, ErrorCode.INVALID_CREDENTIALS, ResponseMessages.INVALID_CREDENTIALS);
    }
}
