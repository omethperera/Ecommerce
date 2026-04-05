package com.ecommerce.userservice.service;

import com.ecommerce.userservice.dto.request.CreateProfileRequest;
import com.ecommerce.userservice.dto.request.UpdateProfileRequest;
import com.ecommerce.userservice.dto.response.AddressResponse;
import com.ecommerce.userservice.dto.response.UserProfileResponse;
import com.ecommerce.userservice.entity.Address;
import com.ecommerce.userservice.entity.UserProfile;
import com.ecommerce.userservice.exception.DuplicateResourceException;
import com.ecommerce.userservice.exception.ResourceNotFoundException;
import com.ecommerce.userservice.repository.UserProfileRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserProfileService {

    private final UserProfileRepository userProfileRepository;

    public UserProfileService(UserProfileRepository userProfileRepository) {
        this.userProfileRepository = userProfileRepository;
    }

    public UserProfileResponse createProfile(CreateProfileRequest request) {
        if (userProfileRepository.existsByUserId(request.getUserId())) {
            throw new DuplicateResourceException("Profile already exists for userId: " + request.getUserId());
        }

        UserProfile profile = new UserProfile();
        profile.setUserId(request.getUserId());
        profile.setFirstName(request.getFirstName());
        profile.setLastName(request.getLastName());
        profile.setPhone(request.getPhone());
        profile.setProfilePictureUrl(request.getProfilePictureUrl());

        UserProfile saved = userProfileRepository.save(profile);
        return mapToResponse(saved);
    }

    @Transactional(readOnly = true)
    public UserProfileResponse getByUserId(Long userId) {
        UserProfile profile = userProfileRepository.findByUserId(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Profile not found for userId: " + userId));

        return mapToResponse(profile);
    }

    public UserProfileResponse updateProfile(Long userId, UpdateProfileRequest request) {
        UserProfile profile = userProfileRepository.findByUserId(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Profile not found for userId: " + userId));

        if (request.getFirstName() != null) {
            profile.setFirstName(request.getFirstName());
        }
        if (request.getLastName() != null) {
            profile.setLastName(request.getLastName());
        }
        if (request.getPhone() != null) {
            profile.setPhone(request.getPhone());
        }
        if (request.getProfilePictureUrl() != null) {
            profile.setProfilePictureUrl(request.getProfilePictureUrl());
        }

        UserProfile updated = userProfileRepository.save(profile);
        return mapToResponse(updated);
    }

    private UserProfileResponse mapToResponse(UserProfile profile) {
        UserProfileResponse response = new UserProfileResponse();
        response.setId(profile.getId());
        response.setUserId(profile.getUserId());
        response.setFirstName(profile.getFirstName());
        response.setLastName(profile.getLastName());
        response.setPhone(profile.getPhone());
        response.setProfilePictureUrl(profile.getProfilePictureUrl());
        response.setCreatedAt(profile.getCreatedAt());
        response.setUpdatedAt(profile.getUpdatedAt());

        List<AddressResponse> addressResponses = profile.getAddresses()
                .stream()
                .map(this::mapAddressToResponse)
                .toList();

        response.setAddresses(addressResponses);
        return response;
    }

    private AddressResponse mapAddressToResponse(Address address) {
        AddressResponse response = new AddressResponse();
        response.setId(address.getId());
        response.setUserId(address.getUserId());
        response.setLabel(address.getLabel());
        response.setAddressLine(address.getAddressLine());
        response.setCity(address.getCity());
        response.setProvince(address.getProvince());
        response.setPostalCode(address.getPostalCode());
        response.setIsDefault(address.getIsDefault());
        return response;
    }
}