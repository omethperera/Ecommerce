package com.ecommerce.adminservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.adminservice.dto.AdminProfileCreateRequest;
import com.ecommerce.adminservice.dto.AdminProfileResponse;
import com.ecommerce.adminservice.entity.Admin;
import com.ecommerce.adminservice.repository.AdminRepository;

@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;

    public AdminProfileResponse createAdminProfile(AdminProfileCreateRequest request) {

        // Check duplicate by userId
        if (adminRepository.existsByUserId(request.getUserId())) {
            throw new RuntimeException("Admin profile already exists for this user");
        }

        // Check duplicate username
        if (adminRepository.existsByUsername(request.getUsername())) {
            throw new RuntimeException("Username already taken");
        }

        // Convert DTO -> Entity
        Admin admin = new Admin();
        admin.setUserId(request.getUserId());
        admin.setUsername(request.getUsername());
        admin.setFirstName(request.getFirstName());
        admin.setLastName(request.getLastName());
        admin.setAddress(request.getAddress());
        admin.setEmail(request.getEmail());
        admin.setProfilePic(request.getProfilePic());

        // Save to DB
        Admin savedAdmin = adminRepository.save(admin);

        // Convert Entity -> Response DTO
        AdminProfileResponse response = new AdminProfileResponse();
        response.setId(savedAdmin.getId());
        response.setUserId(savedAdmin.getUserId());
        response.setUsername(savedAdmin.getUsername());
        response.setFirstName(savedAdmin.getFirstName());
        response.setLastName(savedAdmin.getLastName());
        response.setAddress(savedAdmin.getAddress());
        response.setEmail(savedAdmin.getEmail());
        response.setProfilePic(savedAdmin.getProfilePic());
        response.setIsActive(savedAdmin.getIsActive());

        return response;
    }
}