package com.udongsari.exception;

public class accountNotFoundException extends RuntimeException {
    public accountNotFoundException(String message) {
        super(message);
    }
}