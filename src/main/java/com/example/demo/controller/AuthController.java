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
    
    /**
     * Login endpoint using form parameters
     * 
     * Swagger will show:
     * Parameters:
     *   email (required): string
     *   password (required): string
     * 
     * Response:
     * {
     *   "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
     *   "role": "ADMIN",
     *   "email": "user@example.com"
     * }
     */
    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(
            @RequestParam("email") String email,
            @RequestParam("password") String password) {
        
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
            Map<String, String> error = new HashMap<>();
            error.put("error", "Invalid email or password");
            return ResponseEntity.status(401).body(error);
        }
    }
    
    /**
     * Register endpoint (unchanged)
     */
    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User user) {
        return ResponseEntity.ok(userService.createUser(user));
    }
}