package com.example.library.exception;

public class EmailValidationException extends RuntimeException{
    public EmailValidationException(String message) {
        super(message);
    }
}
