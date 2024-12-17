package com.example.informaciskalab2.bootstrap;

import com.example.informaciskalab2.model.User;
import com.example.informaciskalab2.repository.UserRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class DataHolder {
    private final UserRepository userRepository;
    public static List<User> userList=new ArrayList<>();

    public DataHolder(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
