package com.example.informaciskalab2.web.controller;


import com.example.informaciskalab2.model.User;
import com.example.informaciskalab2.model.exceptions.InvalidCredentialsException;
import com.example.informaciskalab2.repository.InMemoryUserRepository;
import com.example.informaciskalab2.service.AuthService;
import com.example.informaciskalab2.service.OTPService;
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
    private final OTPService otpService;

    public LoginController(AuthService authService, InMemoryUserRepository userRepository, OTPService otpService) {
        this.authService = authService;
        this.userRepository = userRepository;
        this.otpService = otpService;
    }

    @GetMapping
    public String getLoginPage() {
        return "login";
    }

    @PostMapping
    public String login(@RequestParam String email, @RequestParam String password,@RequestParam(required = false) String otp,HttpSession session,Model model) {
        try {
            if (password != null && otp == null) {
                authService.login(email, password);
                authService.generateAndSendOTP(email);
                model.addAttribute("otpRequired", true);
                return "login";
            }

            if (otp != null) {
                boolean isValidOtp = otpService.validateOTP(email, otp);
                if (!isValidOtp) {
                    model.addAttribute("error", "Invalid OTP. Please try again.");
                    model.addAttribute("otpRequired", true);
                    return "login";
                }

                User user = this.userRepository.findByEmail(email).orElseThrow();
                session.setAttribute("user", user);
                return "redirect:/home";
            }

            throw new InvalidCredentialsException("Invalid login attempt");

        } catch (InvalidCredentialsException e) {
            model.addAttribute("error", "Invalid email or password.");
            return "login";
        }
    }
}
