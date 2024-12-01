package com.example.informaciskalab2.web.controller;

import com.example.informaciskalab2.model.User;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home")
public class HomeController {

    @GetMapping
    public String getHomePage(Model model, HttpServletRequest request) {
        User user=(User)request.getSession().getAttribute("user");
        model.addAttribute("user", user);
        return "home";
    }

}
