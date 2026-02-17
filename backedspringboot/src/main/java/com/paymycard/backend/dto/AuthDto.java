package com.paymycard.backend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;

public class AuthDto {

    @Getter
    @Setter
    public static class LoginRequest {
        @NotBlank(message = "Mobile number is required")
        @Pattern(regexp = "^[0-9]{10}$", message = "Mobile number must be 10 digits")
        private String mobile;
    }

    @Getter
    @Setter
    public static class SignupRequest {
        @NotBlank(message = "Full name is required")
        private String fullName;

        @NotBlank(message = "Mobile number is required")
        @Pattern(regexp = "^[0-9]{10}$", message = "Mobile number must be 10 digits")
        private String mobile;

        private boolean privacyAccepted;
    }

    @Getter
    @Setter
    @Builder
    public static class LoginResponse {
        private String token;
        private UserDto user;
    }

    @Getter
    @Setter
    @Builder
    public static class UserDto {
        private String id;
        private String fullName;
        private String mobile;
        private String email;
        private String profilePicture;
    }
}
