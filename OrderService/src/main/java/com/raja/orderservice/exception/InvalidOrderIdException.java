package com.raja.orderservice.exception;

public class InvalidOrderIdException extends RuntimeException {
    public InvalidOrderIdException(String message) {
        super(message);
    }
}
