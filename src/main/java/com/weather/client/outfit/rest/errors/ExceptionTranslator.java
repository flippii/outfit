package com.weather.client.outfit.rest.errors;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Value;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestControllerAdvice
public class ExceptionTranslator {

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(code = NOT_FOUND)
    public SimpleError processException(RuntimeException ex) {
        return new SimpleError(ex.getClass().getName(), ex.getMessage());
    }

    @Value
    @Getter
    @AllArgsConstructor
    public static class SimpleError {

        private String exception;
        private String message;

    }

}
