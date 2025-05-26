package com.cflDevApps.dpDecorator.execeptions;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
public class NotAuthorizedException extends RuntimeException {

    private final HttpStatus status;

    public NotAuthorizedException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }
}
