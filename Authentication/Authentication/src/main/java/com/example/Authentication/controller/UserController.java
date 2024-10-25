package com.example.Authentication.controller;

import com.example.Authentication.model.UserInfo;
import com.example.Authentication.service.JwtService;
import com.example.Authentication.service.Userservices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;

import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class UserController {

    @Autowired
    private Userservices service;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;


    @PostMapping("/register")
    public UserInfo register(@RequestBody UserInfo user) {
        return service.register(user);
    }

    @GetMapping("/validate-token")
    public boolean validateToken(@RequestParam String token) {
        return service.verifyToken(token);
    }

    @PostMapping("/login")
    public String getToken(@RequestBody Map<String, String> user) {
        String username = user.get("username");
        String password = user.get("password");

        System.out.println("user : " + username);
        Authentication authenticate = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password)
        );
        System.out.println("authenticated?? : " + authenticate.isAuthenticated());
        if (authenticate.isAuthenticated()) {
            return service.generateToken(username);
        }
        return null;
    }

}
