package com.example.informaciskalab2.repository;

import com.example.informaciskalab2.bootstrap.DataHolder;
import com.example.informaciskalab2.model.User;
import org.springframework.stereotype.Repository;

import javax.xml.crypto.Data;
import java.util.Optional;

@Repository
public class InMemoryUserRepository {

    public Optional<User> findByEmail(String email) {
        return DataHolder.userList.stream().filter(user -> user.getEmail().equals(email)).findFirst();
    }

    public void deleteByEmail(String email) {
        DataHolder.userList.removeIf(user -> user.getEmail().equals(email));
    }

    public User saveOrUpdate(User user) {
        DataHolder.userList.removeIf(u -> u.getEmail().equals(user.getEmail()));
        DataHolder.userList.add(user);
        return user;
    }

    public Optional<User> findByEmailAndPassword(String email, String password) {
        return DataHolder.userList.stream().filter(user -> user.getEmail().equals(email) && user.getPassword().equals(password)).findFirst();
    }

    public User findByVerificationToken(String token) {
        return DataHolder.userList.stream()
                .filter(user -> token.equals(user.getVerificationCode()))
                .findFirst()
                .orElse(null);
    }

    public User findByToken(String token) {
        return DataHolder.userList.stream().filter(c->token.equals(c.getVerificationCode())).findFirst().orElse(null);
    }
}
