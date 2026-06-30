package com.ecommerce.authservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.ecommerce.authservice.dto.AdminProfileCreateRequest;

@FeignClient(name = "admin-service")
public interface AdminServiceClient {

    @PostMapping("/api/admins/profile")
    void createAdminProfile(@RequestBody AdminProfileCreateRequest request);
}