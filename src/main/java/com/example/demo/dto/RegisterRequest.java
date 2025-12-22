// package com.example.demo.dto;

// import jakarta.validation.constraints.Email;
// import jakarta.validation.constraints.NotBlank;

// public class RegisterRequest {
    
//     @NotBlank(message = "Full name is required")
//     private String fullName;
    
//     @NotBlank(message = "Email is required")
//     @Email(message = "Email should be valid")
//     private String email;
    
//     @NotBlank(message = "Password is required")
//     private String password;
    
//     private String role = "MARKETER";

//     // Constructors
//     public RegisterRequest() {}

//     public RegisterRequest(String fullName, String email, String password, String role) {
//         this.fullName = fullName;
//         this.email = email;
//         this.password = password;
//         this.role = role;
//     }

//     // Getters and Setters
//     public String getFullName() {
//         return fullName;
//     }

//     public void setFullName(String fullName) {
//         this.fullName = fullName;
//     }

//     public String getEmail() {
//         return email;
//     }

//     public void setEmail(String email) {
//         this.email = email;
//     }

//     public String getPassword() {
//         return password;
//     }

//     public void setPassword(String password) {
//         this.password = password;
//     }

//     public String getRole() {
//         return role;
//     }

//     public void setRole(String role) {
//         this.role = role;
//     }
// }