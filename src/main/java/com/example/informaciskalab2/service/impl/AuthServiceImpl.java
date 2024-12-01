package com.example.informaciskalab2.service.Impl;

import com.example.informaciskalab2.model.User;
import com.example.informaciskalab2.model.exceptions.InvalidCredentialsException;
import com.example.informaciskalab2.model.exceptions.PasswordDoNotMatchException;
import com.example.informaciskalab2.repository.InMemoryUserRepository;
import com.example.informaciskalab2.service.AuthService;
import com.example.informaciskalab2.service.EmailService;
import com.example.informaciskalab2.service.OTPService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService {

    private final EmailService emailService;
    private final OTPService otpService;

    public AuthServiceImpl(EmailService emailService, OTPService otpService, InMemoryUserRepository userRepository) {
        this.emailService = emailService;
        this.otpService = otpService;
        this.userRepository = userRepository;
    }

    private final InMemoryUserRepository userRepository;

    @Override
    public User register(String email, String password, String repeatPassword ,String name, String surname) {
        if(!password.equals(repeatPassword)) {
            throw new PasswordDoNotMatchException();
        }
        User user=new User(email,password,name,surname);
        this.userRepository.saveOrUpdate(user);
        return user;
    }

    @Override
    public User login(String email, String password) {
        if(email==null || password==null) {
            throw new InvalidCredentialsException(email);
        }
        return userRepository.findByEmailAndPassword(email,password).orElseThrow(()->new InvalidCredentialsException(email));
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
        int special=0;
        int upperCase=0;
        int number=0;
        int loweCase=0;
        for(int i=0;i<password.length();i++) {
            if(Character.isUpperCase(password.charAt(i))) {
                upperCase++;
            }
            if(Character.isLowerCase(password.charAt(i))) {
                loweCase++;
            }
            if(!Character.isAlphabetic(password.charAt(i)) && !Character.isDigit(password.charAt(i))) {
                special++;
            }
            if(Character.isDigit(password.charAt(i))) {
                number++;
            }
        }
        if(special>0 && upperCase>0 && loweCase>0 && number>0 && password.length()>7) {
            return true;
        }
        return false;
    }
}
