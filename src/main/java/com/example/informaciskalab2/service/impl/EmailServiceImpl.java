package com.example.informaciskalab2.service.impl;

import com.example.informaciskalab2.service.EmailService;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {

    private JavaMailSender mailSender;

    public EmailServiceImpl(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public void sendVerificationEmail(String email, String token) {
        String verificationLink="http://localhost:8080/verify?token="+token;
        SimpleMailMessage message=new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Email verification");
        message.setText("Email verification link: "+verificationLink);
        mailSender.send(message);
    }

    @Override
    public void sendOtpEmail(String email, String otp) {
        SimpleMailMessage message=new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Email OTP");
        message.setText("Your OTP is: "+otp);
        mailSender.send(message);
    }

}
