package com.example.orderservice.exception;

public class OrderAlreadyPickedUpException extends RuntimeException {
    public OrderAlreadyPickedUpException(String message) {
        super(message);
    }
}
