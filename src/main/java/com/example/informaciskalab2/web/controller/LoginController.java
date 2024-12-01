package com.example.informaciskalab2.web.controller;


import com.example.informaciskalab2.model.User;
import com.example.informaciskalab2.model.exceptions.InvalidCredentialsException;
import com.example.informaciskalab2.repository.InMemoryUserRepository;
import com.example.informaciskalab2.service.AuthService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/login")
public class LoginController {
    private final AuthService authService;
    private final InMemoryUserRepository userRepository;

    public LoginController(AuthService authService, InMemoryUserRepository userRepository) {
        this.authService = authService;
        this.userRepository = userRepository;
    }

    @GetMapping
    public String getLoginPage() {
        return "login";
    }

    @PostMapping
    public String login(@RequestParam String email, @RequestParam String password,HttpSession session) {
        try{
            authService.login(email, password);
            User user=this.userRepository.findByEmail(email).orElseThrow();
            session.setAttribute("user",user);
            return "redirect:/home";
        }catch (InvalidCredentialsException e){
            throw new InvalidCredentialsException(email);
        }
    }
}
