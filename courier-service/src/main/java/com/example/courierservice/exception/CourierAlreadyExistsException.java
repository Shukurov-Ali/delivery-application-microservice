package com.example.courierservice.exception;

public class CourierAlreadyExistsException extends RuntimeException {
    public CourierAlreadyExistsException(String message) {
        super(message);
    }
}
