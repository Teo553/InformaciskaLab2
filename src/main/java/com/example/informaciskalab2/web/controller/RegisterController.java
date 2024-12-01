package com.example.informaciskalab2.web.controller;


import com.example.informaciskalab2.model.User;
import com.example.informaciskalab2.model.exceptions.PasswordDoNotMatchException;
import com.example.informaciskalab2.service.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/register")
public class RegisterController {

    private final AuthService authService;

    public RegisterController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping
    public String getRegisterPage() {
        return "register";
    }

    @PostMapping
    public String postRegisterPage(@RequestParam String name, @RequestParam String  surname,@RequestParam String email, @RequestParam String password, @RequestParam String confirmPassword) {
        this.authService.register(email,password,confirmPassword,name,surname);
        return "redirect:/login";
    }

}
