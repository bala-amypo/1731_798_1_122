package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.security.JwtUtil;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    
    @Autowired
    private AuthenticationManager authenticationManager;
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private JwtUtil jwtUtil;
    
    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody Map<String, String> requestBody) {
        // Extract email and password from the request body
        String email = requestBody.get("email");
        String password = requestBody.get("password");
        
        // Validate that email and password are provided
        if (email == null || email.isEmpty()) {
            throw new RuntimeException("Email is required");
        }
        if (password == null || password.isEmpty()) {
            throw new RuntimeException("Password is required");
        }
        
        try {
            // Authenticate the user
            Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email, password)
            );
            
            // Get user details
            User user = userService.getUserByEmail(email);
            
            // Generate JWT token
            String token = jwtUtil.generateToken(user.getEmail(), user.getRole(), user.getId());
            
            // Create response
            Map<String, String> response = new HashMap<>();
            response.put("token", token);
            response.put("role", user.getRole());
            response.put("email", user.getEmail());
            
            return ResponseEntity.ok(response);
            
        } catch (Exception e) {
            throw new RuntimeException("Invalid credentials: " + e.getMessage());
        }
    }
    
    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User user) {
        User createdUser = userService.createUser(user);
        return ResponseEntity.ok(createdUser);
    }
}