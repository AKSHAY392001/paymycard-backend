package com.paymycard.backend.service;

import com.paymycard.backend.dto.AuthDto;
import com.paymycard.backend.model.User;
import com.paymycard.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public AuthDto.UserDto getCurrentUserProfile() {
        String mobile = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByMobile(mobile)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return mapToUserDto(user);
    }

    @Transactional
    public AuthDto.UserDto updateProfile(AuthDto.UserDto userDto) {
        String mobile = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByMobile(mobile)
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setFullName(userDto.getFullName());
        user.setEmail(userDto.getEmail());
        if (userDto.getProfilePicture() != null) {
            user.setProfilePicture(userDto.getProfilePicture());
        }

        user = userRepository.save(user);
        return mapToUserDto(user);
    }

    private AuthDto.UserDto mapToUserDto(User user) {
        return AuthDto.UserDto.builder()
                .id(user.getId().toString())
                .fullName(user.getFullName())
                .mobile(user.getMobile())
                .email(user.getEmail())
                .profilePicture(user.getProfilePicture())
                .build();
    }
}
