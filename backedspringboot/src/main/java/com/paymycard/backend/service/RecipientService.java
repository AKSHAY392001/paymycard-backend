package com.paymycard.backend.service;

import com.paymycard.backend.dto.RecipientDto;
import com.paymycard.backend.model.Recipient;
import com.paymycard.backend.model.User;
import com.paymycard.backend.repository.RecipientRepository;
import com.paymycard.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RecipientService {

    private final RecipientRepository recipientRepository;
    private final UserRepository userRepository;

    public List<RecipientDto> getAllRecipients() {
        User user = getCurrentUser();
        return recipientRepository.findAllByUser(user).stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public RecipientDto addRecipient(RecipientDto dto) {
        User user = getCurrentUser();

        String masked = maskAccountNo(dto.getAccountNo());

        Recipient recipient = Recipient.builder()
                .user(user)
                .bankName(dto.getBankName())
                .accountNoEncrypted(dto.getAccountNo()) // In real app, encrypt this
                .accountNoMasked(masked)
                .ifsc(dto.getIfsc())
                .holderName(dto.getHolderName())
                .build();

        recipient = recipientRepository.save(recipient);
        return mapToDto(recipient);
    }

    @Transactional
    public void deleteRecipient(UUID id) {
        User user = getCurrentUser();
        Recipient recipient = recipientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Recipient not found"));

        if (!recipient.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("Unauthorized");
        }

        recipientRepository.delete(recipient);
    }

    private User getCurrentUser() {
        String mobile = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findByMobile(mobile)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    private RecipientDto mapToDto(Recipient recipient) {
        return RecipientDto.builder()
                .id(recipient.getId().toString())
                .bankName(recipient.getBankName())
                .accountNo(recipient.getAccountNoMasked())
                .ifsc(recipient.getIfsc())
                .holderName(recipient.getHolderName())
                .build();
    }

    private String maskAccountNo(String accountNo) {
        if (accountNo == null || accountNo.length() < 4)
            return "****";
        return "**** " + accountNo.substring(accountNo.length() - 4);
    }
}
