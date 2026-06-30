package com.ecommerce.authservice.service;

import com.ecommerce.authservice.dto.AuthResponse;
import com.ecommerce.authservice.dto.LoginRequest;
import com.ecommerce.authservice.dto.RefreshTokenRequest;
import com.ecommerce.authservice.dto.RegisterRequest;
import com.ecommerce.authservice.entity.RefreshToken;
import com.ecommerce.authservice.entity.User;
import com.ecommerce.authservice.repository.UserRepository;
import com.ecommerce.authservice.security.JwtService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final RefreshTokenService refreshTokenService;
    private final AuthenticationManager authenticationManager;

    public AuthService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder,
                       JwtService jwtService,
                       RefreshTokenService refreshTokenService,
                       AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.refreshTokenService = refreshTokenService;
        this.authenticationManager = authenticationManager;
    }

    // Register

    @Transactional
    public AuthResponse register(RegisterRequest request) {
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new RuntimeException("Username is already taken: " + request.getUsername());
        }
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email is already registered: " + request.getEmail());
        }

        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(
            request.getRole() != null
                ? User.Role.valueOf(request.getRole())
                : User.Role.ROLE_USER
        );
        user.setActive(true);

        User savedUser = userRepository.save(user);

        String accessToken = jwtService.generateAccessToken(savedUser);
        RefreshToken refreshToken = refreshTokenService.createRefreshToken(savedUser);

        return buildAuthResponse(savedUser, accessToken, refreshToken.getToken());
    }

    // login

    public AuthResponse login(LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsernameOrEmail(),
                        request.getPassword()
                )
        );

        // Try to find by username first, then by email
        User user = userRepository.findByUsername(request.getUsernameOrEmail())
                .orElseGet(() -> userRepository.findByEmail(request.getUsernameOrEmail())
                        .orElseThrow(() -> new RuntimeException("User not found")));

        String accessToken = jwtService.generateAccessToken(user);
        RefreshToken refreshToken = refreshTokenService.createRefreshToken(user);

        return buildAuthResponse(user, accessToken, refreshToken.getToken());
    }

    // ─── Refresh Token ────────────────────────────────────────────────────────

    public AuthResponse refreshToken(RefreshTokenRequest request) {
        RefreshToken refreshToken = refreshTokenService.findByToken(request.getRefreshToken())
                .orElseThrow(() -> new RuntimeException("Refresh token not found"));

        refreshTokenService.verifyExpiration(refreshToken);

        User user = refreshToken.getUser();
        String newAccessToken = jwtService.generateAccessToken(user);

        return buildAuthResponse(user, newAccessToken, refreshToken.getToken());
    }

    // ─── Logout ───────────────────────────────────────────────────────────────

    @Transactional
    public void logout(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found: " + username));
        refreshTokenService.revokeTokenByUser(user);
    }

    // ─── Validate Token (called by API Gateway) ───────────────────────────────

    public boolean validateToken(String token) {
        return jwtService.validateToken(token);
    }

    // ─── Private Helper ───────────────────────────────────────────────────────

    private AuthResponse buildAuthResponse(User user, String accessToken, String refreshToken) {
        AuthResponse response = new AuthResponse();
        response.setAccessToken(accessToken);
        response.setRefreshToken(refreshToken);
        response.setTokenType("Bearer");
        response.setUserId(user.getId());
        response.setUsername(user.getUsername());
        response.setEmail(user.getEmail());
        response.setRole(user.getRole().name());
        return response;
    }
}