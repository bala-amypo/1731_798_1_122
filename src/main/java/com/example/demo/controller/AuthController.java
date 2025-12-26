package com.example.demo.controller;

import com.example.demo.dto.LoginRequest;
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
    public ResponseEntity<Map<String, String>> login(@RequestBody LoginRequest loginRequest) {
        String email = loginRequest.getEmail();
        String password = loginRequest.getPassword();
        
        try {
            Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email, password)
            );
            
            // Try different method names - use the one that exists in your UserService
            User user = null;
            
            // Option 1: getByEmail (most common)
            try {
                user = userService.getByEmail(email);
            } catch (Exception e1) {
                // Option 2: findByEmail
                try {
                    user = userService.findByEmail(email);
                } catch (Exception e2) {
                    // Option 3: findUserByEmail
                    try {
                        user = userService.findUserByEmail(email);
                    } catch (Exception e3) {
                        // Option 4: getUserByEmail
                        user = userService.getUserByEmail(email);
                    }
                }
            }
            
            // Or if you know your exact method, use it directly:
            // User user = userService.getUserByEmail(email); // Use the correct method name
            
            String token = jwtUtil.generateToken(user.getEmail(), user.getRole(), user.getId());
            
            Map<String, String> response = new HashMap<>();
            response.put("token", token);
            response.put("role", user.getRole());
            response.put("email", user.getEmail());
            
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            throw new RuntimeException("Invalid credentials");
        }
    }
    
    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User user) {
        // Try different method names for registration
        User savedUser = null;
        
        // Option 1: save (most common)
        try {
            savedUser = userService.save(user);
        } catch (Exception e1) {
            // Option 2: createUser
            try {
                savedUser = userService.createUser(user);
            } catch (Exception e2) {
                // Option 3: registerUser
                try {
                    savedUser = userService.registerUser(user);
                } catch (Exception e3) {
                    // Option 4: create
                    savedUser = userService.create(user);
                }
            }
        }
        
        return ResponseEntity.ok(savedUser);
    }
}