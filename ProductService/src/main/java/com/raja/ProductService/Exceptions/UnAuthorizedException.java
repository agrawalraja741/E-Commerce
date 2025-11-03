package com.raja.ProductService.Exceptions;

public class UnAuthorizedException extends RuntimeException {
    private String message;

    public UnAuthorizedException(String message) {
        super(message);
    }
}
