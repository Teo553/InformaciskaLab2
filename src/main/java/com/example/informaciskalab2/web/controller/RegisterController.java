package com.example.informaciskalab2.web.controller;


import com.example.informaciskalab2.model.User;
import com.example.informaciskalab2.model.exceptions.PasswordDoNotMatchException;
import com.example.informaciskalab2.repository.InMemoryUserRepository;
import com.example.informaciskalab2.service.AuthService;
import com.example.informaciskalab2.service.EmailService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.UUID;

@Controller
@RequestMapping("/register")
public class RegisterController {

    private final AuthService authService;
    private final InMemoryUserRepository userRepository;
    private final EmailService emailService;

    public RegisterController(AuthService authService, InMemoryUserRepository userRepository, EmailService emailService) {
        this.authService = authService;
        this.userRepository = userRepository;
        this.emailService = emailService;
    }

    @GetMapping
    public String getRegisterPage() {
        return "register";
    }

    @PostMapping
    public String postRegisterPage(@RequestParam String name, @RequestParam String  surname,@RequestParam String email, @RequestParam String password, @RequestParam String confirmPassword) {
        String token= UUID.randomUUID().toString();
        this.authService.register(email,password,confirmPassword,name,surname);
        User user = this.userRepository.findByEmail(email).orElseThrow();
        user.setVerified(false);
        user.setVerificationCode(token);
        emailService.sendVerificationEmail(email,token);
        return "redirect:/login";
    }

}
