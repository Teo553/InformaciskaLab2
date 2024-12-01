package com.example.informaciskalab2.service.Impl;

import com.example.informaciskalab2.model.User;
import com.example.informaciskalab2.model.exceptions.InvalidCredentialsException;
import com.example.informaciskalab2.model.exceptions.PasswordDoNotMatchException;
import com.example.informaciskalab2.repository.InMemoryUserRepository;
import com.example.informaciskalab2.service.AuthService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService {

    public AuthServiceImpl(InMemoryUserRepository userRepository) {
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
}
