package com.paymycard.backend.controller;

import com.paymycard.backend.dto.ApiResponse;
import com.paymycard.backend.dto.RecipientDto;
import com.paymycard.backend.service.RecipientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/recipients")
@RequiredArgsConstructor
public class RecipientController {

    private final RecipientService recipientService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<RecipientDto>>> listRecipients() {
        return ResponseEntity.ok(ApiResponse.success(recipientService.getAllRecipients(), "Recipients fetched"));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<RecipientDto>> addRecipient(@RequestBody RecipientDto recipientDto) {
        return ResponseEntity.ok(ApiResponse.success(recipientService.addRecipient(recipientDto), "Recipient added"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteRecipient(@PathVariable UUID id) {
        recipientService.deleteRecipient(id);
        return ResponseEntity.ok(ApiResponse.success(null, "Recipient deleted"));
    }
}
