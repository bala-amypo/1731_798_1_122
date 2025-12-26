package com.influencer.api.dto;

import com.influencer.api.model.User;
import com.influencer.api.model.UserRole;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Authentication response after successful login")
public class AuthResponse {

    @Schema(description = "User ID", example = "1")
    private Long id;

    @Schema(description = "Username", example = "john_doe")
    private String username;

    @Schema(description = "Email address", example = "john.doe@example.com")
    private String email;

    @Schema(description = "User role", example = "MANAGER")
    private UserRole role;

    @Schema(description = "JWT access token")
    private String token;

    @Schema(description = "Token type", example = "Bearer")
    private String tokenType = "Bearer";

    @Schema(description = "Token expiration time in seconds", example = "86400")
    private Long expiresIn;

    @Schema(description = "User's full name", example = "John Doe")
    private String fullName;

    @Schema(description = "Profile image URL", example = "https://example.com/profile.jpg")
    private String profileImage;

    @Schema(description = "Is this the user's first login?", example = "false")
    private boolean firstLogin;

    @Schema(description = "Response message", example = "Login successful")
    private String message;

    @Schema(description = "Login timestamp")
    private LocalDateTime loginTime;

    // Constructor for creating response from User entity
    public AuthResponse(User user, String token, Long expiresIn) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.role = user.getRole();
        this.token = token;
        this.expiresIn = expiresIn;
        this.fullName = user.getFullName();
        this.profileImage = user.getProfileImage();
        this.firstLogin = user.isFirstLogin();
        this.message = "Login successful";
        this.loginTime = LocalDateTime.now();
    }
}