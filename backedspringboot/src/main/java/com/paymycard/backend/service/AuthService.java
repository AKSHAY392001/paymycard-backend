package com.paymycard.backend.service;

import com.paymycard.backend.dto.AuthDto;
import com.paymycard.backend.model.User;
import com.paymycard.backend.repository.UserRepository;
import com.paymycard.backend.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final JwtService jwtService;

    public AuthDto.LoginResponse login(AuthDto.LoginRequest request) {
        User user = userRepository.findByMobile(request.getMobile())
                .orElseThrow(() -> new RuntimeException("User not found with mobile: " + request.getMobile()));

        String token = jwtService.generateToken(user.getMobile());

        return AuthDto.LoginResponse.builder()
                .token(token)
                .user(mapToUserDto(user))
                .build();
    }

    @Transactional
    public AuthDto.LoginResponse signup(AuthDto.SignupRequest request) {
        if (userRepository.findByMobile(request.getMobile()).isPresent()) {
            throw new RuntimeException("Mobile number already registered");
        }

        User user = User.builder()
                .fullName(request.getFullName())
                .mobile(request.getMobile())
                .privacyAccepted(request.isPrivacyAccepted())
                .build();

        user = userRepository.save(user);

        String token = jwtService.generateToken(user.getMobile());

        return AuthDto.LoginResponse.builder()
                .token(token)
                .user(mapToUserDto(user))
                .build();
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
