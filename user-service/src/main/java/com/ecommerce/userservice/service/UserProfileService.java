package com.ecommerce.userservice.service;

import org.springframework.stereotype.Service;

import com.ecommerce.userservice.dto.UserProfileCreateRequest;
import com.ecommerce.userservice.dto.UserProfileResponse;
import com.ecommerce.userservice.entity.UserProfile;
import com.ecommerce.userservice.repository.UserProfileRepository;

@Service
public class UserProfileService {

    private final UserProfileRepository userProfileRepository;

    public UserProfileService(UserProfileRepository userProfileRepository) {
        this.userProfileRepository = userProfileRepository;
    }

    public UserProfileResponse createUserProfile(UserProfileCreateRequest request) {

        // Check if profile already exists for this user
        if (userProfileRepository.existsByUserId(request.getUserId())) {
            throw new RuntimeException("User profile already exists for this userId");
        }

        // Check if username already exists
        if (userProfileRepository.existsByUsername(request.getUsername())) {
            throw new RuntimeException("Username already taken");
        }

        // Create entity
        UserProfile userProfile = new UserProfile();
        userProfile.setUserId(request.getUserId());
        userProfile.setFirstName(request.getFirstName());
        userProfile.setLastName(request.getLastName());
        userProfile.setPhone(request.getPhone());
        userProfile.setUsername(request.getUsername());
        userProfile.setProfilePictureUrl(request.getProfilePictureUrl());
        userProfile.setAddress(request.getAddress());

        // Save to DB
        UserProfile savedProfile = userProfileRepository.save(userProfile);

        UserProfileResponse response = new UserProfileResponse();
        response.setId(savedProfile.getId());
        response.setUserId(savedProfile.getUserId());
        response.setFirstName(savedProfile.getFirstName());
        response.setLastName(savedProfile.getLastName());
        response.setPhone(savedProfile.getPhone());
        response.setUsername(savedProfile.getUsername());
        response.setProfilePictureUrl(savedProfile.getProfilePictureUrl());
        response.setAddress(savedProfile.getAddress());
        response.setCreatedAt(savedProfile.getCreatedAt());
        response.setUpdatedAt(savedProfile.getUpdatedAt());

        return response;
    }
}