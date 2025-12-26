package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.security.JwtUtil;
import com.example.demo.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@Tag(name = "Authentication", description = "Authentication API for user registration and login")
public class AuthController {
    
    @Autowired
    private AuthenticationManager authenticationManager;
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private JwtUtil jwtUtil;
    
    @Operation(summary = "User login", description = "Authenticate user and return JWT token")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Login successful",
                     content = @Content(schema = @Schema(implementation = Map.class))),
        @ApiResponse(responseCode = "401", description = "Invalid credentials",
                     content = @Content(schema = @Schema(implementation = Map.class))),
        @ApiResponse(responseCode = "500", description = "Internal server error",
                     content = @Content(schema = @Schema(implementation = Map.class)))
    })
    @PostMapping("/login")
    public ResponseEntity<?> login(
            @Parameter(description = "Login credentials", required = true)
            @RequestBody Map<String, String> loginRequest) {
        
        String email = loginRequest.get("email");
        String password = loginRequest.get("password");
        
        try {
            Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email, password)
            );
            
            User user = userService.getUserByEmail(email);
            String token = jwtUtil.generateToken(user.getEmail(), user.getRole(), user.getId());
            
            Map<String, String> response = new HashMap<>();
            response.put("token", token);
            response.put("role", user.getRole());
            response.put("email", user.getEmail());
            response.put("message", "Login successful");
            
            return ResponseEntity.ok(response);
            
        } catch (BadCredentialsException e) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "Invalid email or password");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
            
        } catch (AuthenticationException e) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "Authentication failed: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
            
        } catch (Exception e) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "Login error: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }
    
    @Operation(summary = "User registration", description = "Register a new user account")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Registration successful",
                     content = @Content(schema = @Schema(implementation = Map.class))),
        @ApiResponse(responseCode = "400", description = "Bad request (e.g., email already exists)",
                     content = @Content(schema = @Schema(implementation = Map.class))),
        @ApiResponse(responseCode = "500", description = "Internal server error",
                     content = @Content(schema = @Schema(implementation = Map.class)))
    })
    @PostMapping("/register")
    public ResponseEntity<?> register(
            @Parameter(description = "User details for registration", required = true)
            @RequestBody User user) {
        
        try {
            // Don't use client-provided ID
            user.setId(null);
            
            User createdUser = userService.createUser(user);
            
            // Return without password
            Map<String, Object> response = new HashMap<>();
            response.put("id", createdUser.getId());
            response.put("email", createdUser.getEmail());
            response.put("role", createdUser.getRole());
            response.put("createdAt", createdUser.getCreatedAt());
            response.put("message", "Registration successful");
            
            return ResponseEntity.ok(response);
            
        } catch (Exception e) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "Registration failed: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
    }
}