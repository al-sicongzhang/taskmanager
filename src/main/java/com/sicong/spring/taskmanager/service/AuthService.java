package com.sicong.spring.taskmanager.service;

import com.sicong.spring.taskmanager.dto.RegisterRequest;
import com.sicong.spring.taskmanager.dto.LoginRequest;
import com.sicong.spring.taskmanager.dto.AuthResponse;
import com.sicong.spring.taskmanager.model.Role;
import com.sicong.spring.taskmanager.model.User;
import com.sicong.spring.taskmanager.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public String register(RegisterRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            return "Email already in use";
        }

        User user = new User();
        user.setEmail(request.getEmail());
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(Role.ROLE_USER);

        userRepository.save(user);
        return "User registered successfully";
    }

    public AuthResponse login(LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail()).orElse(null);

        if (user == null) {
            return new AuthResponse("login failed: email not found", null);
        }

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            return new AuthResponse("login failed: wrong password", null);
        }

        String token = "fake-jwt-token";
        return new AuthResponse(token, user.getUsername());
    }
}
