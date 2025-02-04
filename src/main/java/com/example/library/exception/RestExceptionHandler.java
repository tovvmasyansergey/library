package com.example.library.exception;

import com.example.library.dto.RestErrorDto;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {EntityNotFoundException.class, HttpClientErrorException.class})
    public ResponseEntity<Object> handleEntityNotFoundException(Exception ex, WebRequest request) {
        RestErrorDto errorDto = RestErrorDto.builder()
                .statusCode(HttpStatus.NOT_FOUND.value())
                .errorMessage(ex.getMessage())
                .build();
        return handleExceptionInternal(ex, errorDto, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler(value = EmailValidationException.class)
    public ResponseEntity<Object> handleEmailAlreadyExistsException(EmailValidationException ex, WebRequest request) {
        RestErrorDto errorDto = RestErrorDto.builder()
                .statusCode(HttpStatus.CONFLICT.value())
                .errorMessage(ex.getMessage())
                .build();
        return handleExceptionInternal(ex, errorDto, new HttpHeaders(), HttpStatus.CONFLICT, request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        return super.handleMethodArgumentNotValid(ex, headers, status, request);
    }
}
