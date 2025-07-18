package com.iotiq.exception;

import com.iotiq.message.ResponseMessages;
import org.springframework.http.HttpStatus;

public class ChatNotFoundException extends ApplicationException {
    public ChatNotFoundException() {
        super(HttpStatus.NOT_FOUND, ErrorCode.CHAT_NOT_FOUND, ResponseMessages.CHAT_NOT_FOUND);
    }
}
