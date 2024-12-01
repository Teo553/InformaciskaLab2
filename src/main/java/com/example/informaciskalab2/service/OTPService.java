package com.example.informaciskalab2.service;

public interface OTPService {
    String generateOTP(String email);
    boolean validateOTP(String email, String otp);
}
