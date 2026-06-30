package com.ecommerce.userservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.ecommerce.userservice.dto.UserProfileCreateRequest;
import com.ecommerce.userservice.dto.UserProfileResponse;
import com.ecommerce.userservice.entity.UserProfile;
import com.ecommerce.userservice.service.UserProfileService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
@Validated
public class UserProfileController {

    private final UserProfileService userProfileService;

    public UserProfileController(UserProfileService userProfileService) {
        this.userProfileService = userProfileService;
    }

    @PostMapping("/profile")
    public ResponseEntity<UserProfileResponse> createUserProfile(@Valid @RequestBody UserProfileCreateRequest request) {
    	UserProfileResponse response = userProfileService.createUserProfile(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}