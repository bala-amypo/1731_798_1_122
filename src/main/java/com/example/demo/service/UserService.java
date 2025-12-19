package com.example.demo.service;

import com.example.demo.dto.AuthResponse;
import com.example.demo.dto.LoginRequest;
import com.example.demo.dto.RegisterRequest;

public interface UserService {
    void registerUser(RegisterRequest request);
    AuthResponse login(LoginRequest request);
}