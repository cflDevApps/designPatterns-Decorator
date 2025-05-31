package com.cflDevApps.dpDecorator.execeptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ProcessFileException extends RuntimeException {

    private final HttpStatus status;

    public ProcessFileException() {
        super("Error processing file");
        this.status = HttpStatus.INTERNAL_SERVER_ERROR; // Default status
    }

    public ProcessFileException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }
}
