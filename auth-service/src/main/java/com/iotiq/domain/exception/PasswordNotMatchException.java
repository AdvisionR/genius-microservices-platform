package com.iotiq.domain.exception;

import com.iotiq.exception.ApplicationException;
import com.iotiq.exception.ErrorCode;
import org.springframework.http.HttpStatus;

import static com.iotiq.message.ResponseMessages.PASSWORDS_DO_NOT_MATCH;

public class PasswordNotMatchException extends ApplicationException {
    public PasswordNotMatchException() {
        super(HttpStatus.BAD_REQUEST, ErrorCode.PASSWORD_NOT_MATCH, PASSWORDS_DO_NOT_MATCH);
    }
}
