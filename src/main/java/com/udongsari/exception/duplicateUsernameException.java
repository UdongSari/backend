package com.udongsari.exception;

public class duplicateUsernameException extends RuntimeException {
    public duplicateUsernameException(String message) {
        super(message);
    }
}