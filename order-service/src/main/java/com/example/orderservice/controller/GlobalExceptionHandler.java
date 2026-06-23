package com.example.orderservice.controller;

import com.example.orderservice.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static com.example.orderservice.constants.ExceptionsCodes.*;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(OrderNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionResponse handleException(OrderNotFoundException exception) {

        return new ExceptionResponse(
                ORDER_NOT_FOUND_CODE,
                exception.getMessage());
    }

    @ExceptionHandler(OrderAlreadyDeliveredException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ExceptionResponse handleException(OrderAlreadyDeliveredException exception) {
        return new ExceptionResponse(
                ORDER_ALREADY_DELIVERED_CODE,
                exception.getMessage()
        );
    }
    @ExceptionHandler(OrderInvalidStatusException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ExceptionResponse handleException(OrderInvalidStatusException exception) {
        return new ExceptionResponse(
                ORDER_INVALID_STATUS_CODE,
                exception.getMessage()
        );
    }
    @ExceptionHandler(OrderAlreadyPickedUpException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ExceptionResponse handleException(OrderAlreadyPickedUpException exception) {
        return new ExceptionResponse(
                ORDER_ALREADY_PICKED_UP_CODE,
                exception.getMessage()
        );
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionResponse handleValidationException(MethodArgumentNotValidException exception) {

        String message = exception.getBindingResult()
                .getFieldErrors()
                .get(0)
                .getDefaultMessage();

        return new ExceptionResponse(
                VALIDATION_ERROR_CODE,
                message
        );
    }
}
