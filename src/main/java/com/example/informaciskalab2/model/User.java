package com.example.informaciskalab2.model;

import lombok.Data;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Data
public class User {
    private String hashedEmail;
    private String hashedPassword;
    private String hashedFirstName;
    private String hashedLastName;
    private String firstName;
    private boolean isVerified;
    private String verificationCode;

    public User(String email, String password, String firstName, String lastName) {
        this.hashedPassword = hashWithSalt(password);
        this.hashedEmail = hashWithSalt(email);
        this.hashedFirstName = hashWithSalt(firstName);
        this.hashedLastName = hashWithSalt(lastName);
        this.isVerified=false;
    }

    private String hashWithSalt(String input) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(input);
    }
}
