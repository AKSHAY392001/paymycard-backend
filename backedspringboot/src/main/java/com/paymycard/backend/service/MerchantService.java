package com.paymycard.backend.service;

import com.paymycard.backend.dto.MerchantDto;
import com.paymycard.backend.model.Merchant;
import com.paymycard.backend.model.User;
import com.paymycard.backend.repository.MerchantRepository;
import com.paymycard.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MerchantService {

    private final MerchantRepository merchantRepository;
    private final UserRepository userRepository;

    public MerchantDto getMerchantProfile() {
        User user = getCurrentUser();
        return merchantRepository.findByUser(user)
                .map(this::mapToDto)
                .orElse(null);
    }

    @Transactional
    public MerchantDto createOrUpdateMerchant(MerchantDto dto) {
        User user = getCurrentUser();
        Merchant merchant = merchantRepository.findByUser(user)
                .orElse(new Merchant());

        merchant.setUser(user);
        merchant.setName(dto.getName());
        merchant.setAddress(dto.getAddress());
        merchant.setOwner(dto.getOwner());
        merchant.setMobile(dto.getMobile());
        merchant.setEmail(dto.getEmail());

        merchant = merchantRepository.save(merchant);
        return mapToDto(merchant);
    }

    @Transactional
    public void deleteMerchant() {
        User user = getCurrentUser();
        merchantRepository.findByUser(user)
                .ifPresent(merchantRepository::delete);
    }

    private User getCurrentUser() {
        String mobile = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findByMobile(mobile)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    private MerchantDto mapToDto(Merchant merchant) {
        return MerchantDto.builder()
                .id(merchant.getId().toString())
                .name(merchant.getName())
                .address(merchant.getAddress())
                .owner(merchant.getOwner())
                .mobile(merchant.getMobile())
                .email(merchant.getEmail())
                .build();
    }
}
