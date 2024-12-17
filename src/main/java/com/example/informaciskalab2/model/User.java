package com.example.informaciskalab2.model;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Data
@Entity
@Table( name="users" )
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private String hashedPassword;

    private String firstName;

    private String lastName;

    private boolean isVerified;

    private String verificationCode;

    public User(String email, String password, String firstName, String lastName) {
        this.hashedPassword = hashWithSalt(password);
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.isVerified=false;
    }

    public User() {

    }

    private String hashWithSalt(String input) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(input);
    }
}
