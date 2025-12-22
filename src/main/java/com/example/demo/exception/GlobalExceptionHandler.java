// package com.example.demo.exception;

// import com.example.demo.dto.ApiResponse;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.security.authentication.BadCredentialsException;
// import org.springframework.web.bind.annotation.ExceptionHandler;
// import org.springframework.web.bind.annotation.ControllerAdvice;
// import org.springframework.web.bind.annotation.ResponseStatus;
// import org.springframework.web.context.request.WebRequest;

// @ControllerAdvice
// public class GlobalExceptionHandler {

//     @ExceptionHandler(ResourceNotFoundException.class)
//     @ResponseStatus(HttpStatus.NOT_FOUND)
//     public ResponseEntity<ApiResponse<?>> handleResourceNotFoundException(
//             ResourceNotFoundException ex, WebRequest request) {
//         ApiResponse<?> response = ApiResponse.error(ex.getMessage());
//         return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
//     }

//     @ExceptionHandler(IllegalArgumentException.class)
//     @ResponseStatus(HttpStatus.BAD_REQUEST)
//     public ResponseEntity<ApiResponse<?>> handleIllegalArgumentException(
//             IllegalArgumentException ex, WebRequest request) {
//         ApiResponse<?> response = ApiResponse.error(ex.getMessage());
//         return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
//     }

//     @ExceptionHandler(BadCredentialsException.class)
//     @ResponseStatus(HttpStatus.UNAUTHORIZED)
//     public ResponseEntity<ApiResponse<?>> handleBadCredentialsException(
//             BadCredentialsException ex, WebRequest request) {
//         ApiResponse<?> response = ApiResponse.error("Invalid email or password");
//         return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
//     }

//     @ExceptionHandler(RuntimeException.class)
//     @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
//     public ResponseEntity<ApiResponse<?>> handleRuntimeException(
//             RuntimeException ex, WebRequest request) {
//         ApiResponse<?> response = ApiResponse.error("An unexpected error occurred: " + ex.getMessage());
//         return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
//     }

//     @ExceptionHandler(Exception.class)
//     @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
//     public ResponseEntity<ApiResponse<?>> handleGenericException(
//             Exception ex, WebRequest request) {
//         ApiResponse<?> response = ApiResponse.error("An internal server error occurred");
//         return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
//     }
// }