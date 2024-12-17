//package com.example.informaciskalab2.repository;
//
//import com.example.informaciskalab2.bootstrap.DataHolder;
//import com.example.informaciskalab2.model.User;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.stereotype.Repository;
//
//import javax.xml.crypto.Data;
//import java.util.Optional;
//
//@Repository
//public class InMemoryUserRepository {
//    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//
//    public Optional<User> findByEmail(String email) {
//        return DataHolder.userList.stream().filter(user -> encoder.matches(email,user.getHashedEmail())).findFirst();
//    }
//
//    public void deleteByEmail(String email) {
//        DataHolder.userList.removeIf(user -> encoder.matches(email,user.getHashedEmail()));
//    }
//
//    public User saveOrUpdate(User user) {
//        DataHolder.userList.removeIf(u -> encoder.matches(u.getHashedEmail(),user.getHashedEmail()));
//        DataHolder.userList.add(user);
//        return user;
//    }
//
//    public Optional<User> findByEmailAndPassword(String email, String password) {
//        return DataHolder.userList.stream().filter(user -> encoder.matches(email,user.getHashedEmail()) && encoder.matches(password,user.getHashedPassword())).findFirst();
//    }
//
//    public User findByVerificationToken(String token) {
//        return DataHolder.userList.stream()
//                .filter(user -> token.equals(user.getVerificationCode()))
//                .findFirst()
//                .orElse(null);
//    }
//
//    public User findByToken(String token) {
//        return DataHolder.userList.stream().filter(c->token.equals(c.getVerificationCode())).findFirst().orElse(null);
//    }
//}
