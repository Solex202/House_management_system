package com.semicolon.africa.House.Management.System.exception;

public class PasswordMustMatchException extends RuntimeException {
    public PasswordMustMatchException(String message) {
        super(message);
    }
}
