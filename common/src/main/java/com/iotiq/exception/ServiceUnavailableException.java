package com.iotiq.exception;

import com.iotiq.enums.ServiceName;
import org.springframework.http.HttpStatus;

public class ServiceUnavailableException extends ApplicationException{
    public ServiceUnavailableException(ServiceName service) {
        super(HttpStatus.SERVICE_UNAVAILABLE, ErrorCode.SERVICE_UNAVAILABLE, service.displayName() + " is unavailable");
    }
}