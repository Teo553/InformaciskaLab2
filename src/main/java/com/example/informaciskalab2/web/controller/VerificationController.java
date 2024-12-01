package com.example.informaciskalab2.web.controller;

import com.example.informaciskalab2.model.User;
import com.example.informaciskalab2.repository.InMemoryUserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/verify")
public class VerificationController {
    private final InMemoryUserRepository userRepository;

    public VerificationController(InMemoryUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping
    public String verification(@RequestParam("token")String token) {
        User user = userRepository.findByVerificationToken(token);
        if (user != null) {
            user.setVerified(true);
            user.setVerificationCode(null);
            userRepository.saveOrUpdate(user);
        }
        return "redirect:/login";
    }

}
