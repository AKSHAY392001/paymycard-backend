package com.paymycard.backend.controller;

import com.paymycard.backend.dto.ApiResponse;
import com.paymycard.backend.dto.AuthDto;
import com.paymycard.backend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/profile")
    public ResponseEntity<ApiResponse<AuthDto.UserDto>> getProfile() {
        return ResponseEntity.ok(ApiResponse.success(userService.getCurrentUserProfile(), "Profile fetched"));
    }

    @PutMapping("/profile")
    public ResponseEntity<ApiResponse<AuthDto.UserDto>> updateProfile(@RequestBody AuthDto.UserDto userDto) {
        return ResponseEntity.ok(ApiResponse.success(userService.updateProfile(userDto), "Profile updated"));
    }
}
