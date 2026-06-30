package com.ecommerce.adminservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ecommerce.adminservice.dto.AdminProfileCreateRequest;
import com.ecommerce.adminservice.dto.AdminProfileResponse;
import com.ecommerce.adminservice.service.AdminService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/admins")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @PostMapping("/profile")
    public ResponseEntity<?> createAdminProfile(@Valid @RequestBody AdminProfileCreateRequest request) {
        try {
            AdminProfileResponse response = adminService.createAdminProfile(request);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (RuntimeException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}