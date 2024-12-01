package com.example.informaciskalab2.model.exceptions;

public class PasswordDoNotMatchException extends RuntimeException {
    public PasswordDoNotMatchException() {
        super("Password do not match");
    }
}
