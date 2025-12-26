package com.influencer.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Password change request")
public class PasswordChangeRequest {

    @Schema(
        description = "Current password",
        example = "OldPassword123!",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotBlank(message = "Current password is required")
    private String currentPassword;

    @Schema(
        description = "New password",
        example = "NewSecurePassword123!",
        requiredMode = Schema.RequiredMode.REQUIRED,
        minLength = 8
    )
    @NotBlank(message = "New password is required")
    @Size(min = 8, message = "New password must be at least 8 characters long")
    @Pattern(
        regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,}$",
        message = "Password must contain at least one digit, one lowercase, one uppercase, one special character, and no spaces"
    )
    private String newPassword;

    @Schema(
        description = "Confirm new password",
        example = "NewSecurePassword123!",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotBlank(message = "Confirm password is required")
    private String confirmNewPassword;
}