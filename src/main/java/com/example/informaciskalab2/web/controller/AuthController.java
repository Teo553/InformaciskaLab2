package com.example.informaciskalab2.web.controller;

import com.example.informaciskalab2.service.AuthService;
import com.example.informaciskalab2.service.OTPService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;
    private final OTPService otpService;

    public AuthController(AuthService authService, OTPService otpService) {
        this.authService = authService;
        this.otpService = otpService;
    }

    @PostMapping("/send-otp")
    public ResponseEntity<String> sendOTP(@RequestParam String email){
        authService.sendLoginOTP(email);
        return ResponseEntity.ok("OTP sent");
    }

    @PostMapping("validate-otp")
    public ResponseEntity<String> validateOTP(@RequestParam String email, @RequestParam String otp) {
        boolean isValid = otpService.validateOTP(email, otp);
        if (isValid) {
            return ResponseEntity.ok("Login successful.");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid OTP.");
        }
    }

}
