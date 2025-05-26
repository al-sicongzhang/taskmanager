package com.sicong.spring.taskmanager.controller;

import com.sicong.spring.taskmanager.dto.RegisterRequest;
import com.sicong.spring.taskmanager.dto.LoginRequest;
import com.sicong.spring.taskmanager.dto.AuthResponse;
import com.sicong.spring.taskmanager.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequest request) {
        String result = authService.register(request);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        AuthResponse response = authService.login(request);

        if (response.getUsername() == null) {
            return ResponseEntity.status(401).body(response.getToken());
        }

        return ResponseEntity.ok(response);
    }
}
