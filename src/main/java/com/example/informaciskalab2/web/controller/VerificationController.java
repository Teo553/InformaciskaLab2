package com.example.informaciskalab2.web.controller;

import com.example.informaciskalab2.model.User;
import com.example.informaciskalab2.repository.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/verify")
public class VerificationController {
    private final UserRepository userRepository;

    public VerificationController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping
    public String verification(@RequestParam("token")String token) {
        User user = userRepository.findUserByVerificationCode(token);
        if (user != null) {
            user.setVerified(true);
            user.setVerificationCode(null);
            userRepository.save(user);
        }
        return "redirect:/login";
    }

}
