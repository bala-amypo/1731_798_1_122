package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.security.JwtUtil;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@PostMapping("/login")
public ResponseEntity<?> login(@RequestBody Map<String, String> loginRequest) {
    String email = loginRequest.get("email");
    String password = loginRequest.get("password");
    
    try {
        System.out.println("Attempting login for: " + email); // Debug log
        
        Authentication auth = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(email, password)
        );
        
        System.out.println("Authentication successful for: " + email); // Debug log
        
        User user = userService.getUserByEmail(email);
        String token = jwtUtil.generateToken(user.getEmail(), user.getRole(), user.getId());
        
        Map<String, String> response = new HashMap<>();
        response.put("token", token);
        response.put("role", user.getRole());
        response.put("email", user.getEmail());
        response.put("message", "Login successful");
        
        return ResponseEntity.ok(response);
        
    } catch (BadCredentialsException e) {
        System.err.println("Bad credentials for: " + email);
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("error", "Invalid email or password");
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
        
    } catch (AuthenticationException e) {
        System.err.println("Authentication failed for: " + email + " - " + e.getMessage());
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("error", "Authentication failed: " + e.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
        
    } catch (Exception e) {
        System.err.println("Login error for: " + email + " - " + e.getMessage());
        e.printStackTrace(); // This will show in logs
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("error", "Login failed: " + e.getClass().getSimpleName());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }
}