package com.example.informaciskalab2.model.exceptions;

public class InvalidCredentialsException extends RuntimeException {
    public InvalidCredentialsException(String email) {
        super(String.format("Invalid credentials for email: %s", email));
    }
}
