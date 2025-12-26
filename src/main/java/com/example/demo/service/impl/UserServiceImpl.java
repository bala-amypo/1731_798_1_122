// package com.example.demo.service.impl;

// import com.example.demo.exception.ResourceNotFoundException;
// import com.example.demo.model.User;
// import com.example.demo.repository.UserRepository;
// import com.example.demo.service.UserService;
// import org.springframework.security.crypto.password.PasswordEncoder;
// import org.springframework.stereotype.Service;

// @Service
// public class UserServiceImpl implements UserService {

//     private final UserRepository userRepository;
//     private final PasswordEncoder passwordEncoder;

//     public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
//         this.userRepository = userRepository;
//         this.passwordEncoder = passwordEncoder;
//     }

//     @Override
//     public User registerUser(User user) {
//         // Check if email already exists
//         if (userRepository.existsByEmail(user.getEmail())) {
//             throw new RuntimeException("Email already exists");
//         }
        
//         // If role is not provided, default to MARKETER
//         if (user.getRole() == null || user.getRole().isEmpty()) {
//             user.setRole("MARKETER");
//         }
        
//         // Hash the password
//         user.setPassword(passwordEncoder.encode(user.getPassword()));
        
//         return userRepository.save(user);
//     }

//     @Override
//     public User findByEmail(String email) {
//         return userRepository.findByEmail(email)
//                 .orElseThrow(() -> new ResourceNotFoundException("User not found with email: " + email));
//     }

//     @Override
//     public User findById(Long id) {
//         return userRepository.findById(id)
//                 .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
//     }
// }
