package com.paymycard.backend.controller;

import com.paymycard.backend.dto.ApiResponse;
import com.paymycard.backend.dto.UserBankDto;
import com.paymycard.backend.service.UserBankService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/user/banks")
@RequiredArgsConstructor
public class UserBankController {

    private final UserBankService userBankService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<UserBankDto>>> listUserBanks() {
        return ResponseEntity.ok(ApiResponse.success(userBankService.getUserBanks(), "User bank accounts fetched"));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<UserBankDto>> addUserBank(@RequestBody UserBankDto userBankDto) {
        return ResponseEntity
                .ok(ApiResponse.success(userBankService.addUserBank(userBankDto), "Bank account added successfully"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteUserBank(@PathVariable UUID id) {
        userBankService.deleteUserBank(id);
        return ResponseEntity.ok(ApiResponse.success(null, "Bank account deleted successfully"));
    }
}
