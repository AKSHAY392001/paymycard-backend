package com.paymycard.backend.controller;

import com.paymycard.backend.dto.ApiResponse;
import com.paymycard.backend.dto.MerchantDto;
import com.paymycard.backend.service.MerchantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/merchant")
@RequiredArgsConstructor
public class MerchantController {

    private final MerchantService merchantService;

    @GetMapping("/profile")
    public ResponseEntity<ApiResponse<MerchantDto>> getProfile() {
        return ResponseEntity.ok(ApiResponse.success(merchantService.getMerchantProfile(), "Merchant profile fetched"));
    }

    @PostMapping("/profile")
    public ResponseEntity<ApiResponse<MerchantDto>> saveProfile(@RequestBody MerchantDto merchantDto) {
        return ResponseEntity
                .ok(ApiResponse.success(merchantService.createOrUpdateMerchant(merchantDto), "Merchant profile saved"));
    }

    @PutMapping("/profile")
    public ResponseEntity<ApiResponse<MerchantDto>> updateProfile(@RequestBody MerchantDto merchantDto) {
        return ResponseEntity.ok(
                ApiResponse.success(merchantService.createOrUpdateMerchant(merchantDto), "Merchant profile updated"));
    }

    @DeleteMapping("/profile")
    public ResponseEntity<ApiResponse<Void>> deleteProfile() {
        merchantService.deleteMerchant();
        return ResponseEntity.ok(ApiResponse.success(null, "Merchant profile deleted"));
    }
}
