package com.example.informaciskalab2.service;

import com.example.informaciskalab2.model.User;

import java.util.Optional;

public interface AuthService {

    User register(String email, String password, String repeatPassword ,String name, String surname);

    User login(String email, String password);


}
