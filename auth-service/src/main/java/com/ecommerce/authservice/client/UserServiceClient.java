package com.ecommerce.authservice.client;

import com.ecommerce.authservice.dto.UserProfileCreateRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "user-service")
public interface UserServiceClient {

    @PostMapping("/api/users/profile")
    void createUserProfile(@RequestBody UserProfileCreateRequest request);
}