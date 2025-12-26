package com.influencer.api.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(description = "Error response for API failures")
public class ErrorResponse {

    @Schema(description = "HTTP status code", example = "400")
    private int status;

    @Schema(description = "Error message", example = "Invalid input parameters")
    private String message;

    @Schema(description = "Detailed error description", example = "Email format is invalid")
    private String details;

    @Schema(description = "Error timestamp")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime timestamp;

    @Schema(description = "API path where error occurred", example = "/api/auth/login")
    private String path;

    @Schema(description = "List of field validation errors")
    private List<FieldError> fieldErrors;

    @Schema(description = "Additional error data")
    private Map<String, Object> additionalData;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(description = "Field validation error")
    public static class FieldError {
        @Schema(description = "Field name", example = "email")
        private String field;
        
        @Schema(description = "Error message", example = "Email is required")
        private String message;
        
        @Schema(description = "Rejected value", example = "")
        private Object rejectedValue;
    }
}