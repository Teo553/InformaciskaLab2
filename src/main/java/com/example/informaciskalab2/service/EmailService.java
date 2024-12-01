package com.example.informaciskalab2.service;

import org.springframework.mail.javamail.JavaMailSender;

public interface EmailService {
    void sendVerificationEmail(String email,String token);
}
