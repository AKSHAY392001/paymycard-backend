package com.paymycard.backend.service;

import com.paymycard.backend.dto.UserBankDto;
import com.paymycard.backend.model.User;
import com.paymycard.backend.model.UserBank;
import com.paymycard.backend.repository.UserBankRepository;
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
public class UserBankService {

    private final UserBankRepository userBankRepository;
    private final UserRepository userRepository;

    public List<UserBankDto> getUserBanks() {
        User user = getCurrentUser();
        return userBankRepository.findAllByUser(user).stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public UserBankDto addUserBank(UserBankDto dto) {
        User user = getCurrentUser();

        String masked = maskAccountNo(dto.getAccountNo());

        UserBank userBank = UserBank.builder()
                .user(user)
                .bankName(dto.getBankName())
                .accountNoEncrypted(dto.getAccountNo()) // In real app, encrypt this
                .accountNoMasked(masked)
                .ifsc(dto.getIfsc())
                .holderName(dto.getHolderName())
                .isPrimary(dto.isPrimary())
                .build();

        userBank = userBankRepository.save(userBank);
        return mapToDto(userBank);
    }

    @Transactional
    public void deleteUserBank(UUID id) {
        User user = getCurrentUser();
        UserBank userBank = userBankRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Bank account not found"));

        if (!userBank.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("Unauthorized");
        }

        userBankRepository.delete(userBank);
    }

    private User getCurrentUser() {
        String mobile = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findByMobile(mobile)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    private UserBankDto mapToDto(UserBank userBank) {
        return UserBankDto.builder()
                .id(userBank.getId().toString())
                .bankName(userBank.getBankName())
                .accountNo(userBank.getAccountNoMasked())
                .ifsc(userBank.getIfsc())
                .holderName(userBank.getHolderName())
                .isPrimary(userBank.isPrimary())
                .build();
    }

    private String maskAccountNo(String accountNo) {
        if (accountNo == null || accountNo.length() < 4)
            return "****";
        return "**** " + accountNo.substring(accountNo.length() - 4);
    }
}
