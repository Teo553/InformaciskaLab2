package com.example.informaciskalab2.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminOnlyController {
    @GetMapping
    public String adminOnly() {
        return "admin-only";
    }
}
