package com.backend.backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.BAD_REQUEST) 
public class InvalidSequenceException extends RuntimeException {
    public InvalidSequenceException(String message) {
        super(message);
    }
}