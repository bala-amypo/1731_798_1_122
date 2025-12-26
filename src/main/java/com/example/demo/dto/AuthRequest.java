package com.influencer.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Authentication request for user login")
public class AuthRequest {

    @Schema(
        description = "User's email address",
        example = "john.doe@example.com",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotBlank(message = "Email is required")
    @Email(message = "Please provide a valid email address")
    private String email;

    @Schema(
        description = "User's password",
        example = "SecurePassword123!",
        requiredMode = Schema.RequiredMode.REQUIRED,
        minLength = 6
    )
    @NotBlank(message = "Password is required")
    @Size(min = 6, message = "Password must be at least 6 characters long")
    private String password;

    @Schema(
        description = "Remember me option for extended token validity",
        example = "false",
        defaultValue = "false"
    )
    private boolean rememberMe = false;
}