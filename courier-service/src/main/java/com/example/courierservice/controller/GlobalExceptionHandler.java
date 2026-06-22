package com.example.courierservice.controller;

import com.example.courierservice.exception.CourierAlreadyExistsException;
import com.example.courierservice.exception.CourierNotFoundException;
import com.example.courierservice.exception.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static com.example.courierservice.constants.ExceptionsCodes.COURIER_ALREADY_EXISTS_CODE;
import static com.example.courierservice.constants.ExceptionsCodes.COURIER_NOT_FOUND_CODE;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CourierNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionResponse handleException(CourierNotFoundException exception) {
    return new ExceptionResponse(COURIER_NOT_FOUND_CODE, exception.getMessage());
    }

    @ExceptionHandler(CourierAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ExceptionResponse handleException(CourierAlreadyExistsException exception) {
        return new ExceptionResponse(COURIER_ALREADY_EXISTS_CODE, exception.getMessage());
    }

}
