package com.example.paymentservice.controller;

import com.example.paymentservice.exception.ExceptionResponse;
import com.example.paymentservice.exception.PaymentNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static com.example.paymentservice.constants.ExceptionCodes.PAYMENT_NOT_FOUND_CODE;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(PaymentNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionResponse handleException(PaymentNotFoundException exception) {
    return new ExceptionResponse(
            PAYMENT_NOT_FOUND_CODE,
            exception.getMessage());
    }

}
