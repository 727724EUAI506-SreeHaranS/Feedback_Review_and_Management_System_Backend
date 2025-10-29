package com.example.demo.Controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Config.JwtUtil;
import com.example.demo.Model.AuthResponse;
import com.example.demo.Model.LoginRequest;
import com.example.demo.Model.Role;
import com.example.demo.Model.User;
import com.example.demo.Service.UserService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        try {
            logger.info("Registration attempt for username: {}", user.getUsername());
            User registeredUser = userService.register(user);
            logger.info("User registered successfully: {}", registeredUser.getUsername());
            return ResponseEntity.ok(registeredUser);
        } catch (RuntimeException e) {
            logger.error("Registration failed for username: {}, error: {}", user.getUsername(), e.getMessage());
            return ResponseEntity.status(409).body("Registration failed: " + e.getMessage());
        }
    }

    @PostMapping("/register-admin")
    public ResponseEntity<?> registerAdmin(@RequestBody User user) {
        try {
            user.setRole(Role.ADMIN);
            User registeredUser = userService.register(user);
            return ResponseEntity.ok(registeredUser);
        } catch (RuntimeException e) {
            return ResponseEntity.status(409).body("Admin registration failed: " + e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        try {
            if (request.getUsername() == null || request.getPassword() == null) {
                return ResponseEntity.status(400).body("Username and password are required");
            }
            
            User user = userService.authenticate(request.getUsername(), request.getPassword());
            if (user != null) {
                String token = jwtUtil.generateToken(user.getUsername());
                return ResponseEntity.ok(new AuthResponse(token, user.getRole().toString()));
            }
            return ResponseEntity.status(401).body("Invalid credentials");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Login failed: " + e.getMessage());
        }
    }
}
