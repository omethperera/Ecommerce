package com.ecommerce.userservice.controller;

import com.ecommerce.userservice.dto.response.ApiResponse;
import com.ecommerce.userservice.dto.response.UserProfileResponse;
import com.ecommerce.userservice.service.UserProfileService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/internal/users")
public class InternalUserController {

    private final UserProfileService userProfileService;

    public InternalUserController(UserProfileService userProfileService) {
        this.userProfileService = userProfileService;
    }

    @GetMapping("/{userId}/profile")
    public ResponseEntity<ApiResponse<UserProfileResponse>> getUserProfileInternal(@PathVariable Long userId) {
        UserProfileResponse response = userProfileService.getByUserId(userId);
        return ResponseEntity.ok(new ApiResponse<>("Internal profile fetch successful", response));
    }
}