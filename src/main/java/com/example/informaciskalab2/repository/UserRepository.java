package com.example.informaciskalab2.repository;

import com.example.informaciskalab2.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    public User findUserByEmailAndHashedPassword(String email, String password);
    public User findUserByEmail(String email);
    public User findUserByVerificationCode(String verificationCode);
}
