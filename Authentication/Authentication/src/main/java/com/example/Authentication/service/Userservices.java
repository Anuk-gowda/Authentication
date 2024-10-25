package com.example.Authentication.service;

import com.example.Authentication.model.UserInfo;
import com.example.Authentication.repository.UserinfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class Userservices {
    @Autowired
    JwtService jwtService;

    @Autowired
    private UserinfoRepository repo;


    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserInfo register(UserInfo user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return repo.saveAndFlush(user);
    }

    public String generateToken(String name) {
        return jwtService.generateToken(name);
    }

    public boolean verifyToken(String token) {
        jwtService.validateToken(token);
        return true;
    }
}