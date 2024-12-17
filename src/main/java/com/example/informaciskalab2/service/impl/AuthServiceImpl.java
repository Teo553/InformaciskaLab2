package com.example.informaciskalab2.service.impl;

import com.example.informaciskalab2.model.User;
import com.example.informaciskalab2.model.exceptions.InvalidCredentialsException;
import com.example.informaciskalab2.model.exceptions.PasswordDoNotMatchException;
import com.example.informaciskalab2.repository.UserRepository;
import com.example.informaciskalab2.service.AuthService;
import com.example.informaciskalab2.service.EmailService;
import com.example.informaciskalab2.service.OTPService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService {
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    private final EmailService emailService;
    private final OTPService otpService;

    public AuthServiceImpl(EmailService emailService, OTPService otpService, UserRepository userRepository) {
        this.emailService = emailService;
        this.otpService = otpService;
        this.userRepository = userRepository;
    }

    private final UserRepository userRepository;

    @Override
    public User register(String email, String password, String repeatPassword ,String name, String surname) {
        if(!password.equals(repeatPassword)) {
            throw new PasswordDoNotMatchException();
        }
        User user=new User(email,password,name,surname);
        this.userRepository.save(user);
        return user;
    }

    @Override
    public User login(String email, String password) {
        if(email==null || password==null) {
            throw new InvalidCredentialsException(email);
        }
        return userRepository.findUserByEmailAndHashedPassword(email,encoder.encode(password));
    }

    @Override
    public void sendLoginOTP(String email) {
        String otp=otpService.generateOTP(email);
        emailService.sendVerificationEmail(email,otp);
    }

    @Override
    public void generateAndSendOTP(String email) {
        String otp = otpService.generateOTP(email);
        emailService.sendOtpEmail(email,otp);
    }

    @Override
    public boolean checkPasswordWeakness(String password) {
        if (password == null || password.isEmpty()) {
            return true;
        }
        if (password.length() < 6) {
            return true;
        }
        if (!password.matches(".*[A-Za-z].*")) {
            return true;
        }
        if (!password.matches(".*\\d.*")) {
            return true;
        }
        return false;
    }
}
