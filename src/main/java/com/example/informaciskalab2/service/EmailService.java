package com.example.informaciskalab2.service;

import org.springframework.mail.javamail.JavaMailSender;

public interface EmailService {
    void sendVerificationEmail(String email,String token);
    void sendOtpEmail(String email,String otp);
}
