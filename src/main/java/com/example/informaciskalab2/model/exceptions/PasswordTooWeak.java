package com.example.informaciskalab2.model.exceptions;

public class PasswordTooWeak extends RuntimeException {
    public PasswordTooWeak() {
        super("Your password is too weak!\n"+"Your password must be at least 8 characters long have a special character and include at least one digit");
    }
}
