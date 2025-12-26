package com.influencer.api.dto;

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
@Schema(description = "User registration response")
public class RegisterResponse {

    @Schema(description = "User ID", example = "1")
    private Long id;

    @Schema(description = "Username", example = "john_doe")
    private String username;

    @Schema(description = "Email address", example = "john.doe@example.com")
    private String email;

    @Schema(description = "User role", example = "USER")
    private UserRole role;

    @Schema(description = "Full name", example = "John Doe")
    private String fullName;

    @Schema(description = "Is account active?", example = "true")
    private boolean active;

    @Schema(description = "Is this the first login?", example = "true")
    private boolean firstLogin;

    @Schema(description = "Account creation timestamp")
    private LocalDateTime createdAt;

    @Schema(description = "Response message", example = "Registration successful")
    private String message;
}