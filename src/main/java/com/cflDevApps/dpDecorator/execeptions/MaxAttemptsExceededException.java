package com.cflDevApps.dpDecorator.execeptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class MaxAttemptsExceededException extends RuntimeException {

    private final HttpStatus status;

    public MaxAttemptsExceededException() {
        super("You have exceeded the maximum number of login attempts. Please try again later.");
        this.status = HttpStatus.TOO_MANY_REQUESTS; // Default status
    }

    public MaxAttemptsExceededException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }
}
