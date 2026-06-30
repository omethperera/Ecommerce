package com.ecommerce.userservice.controller;

import com.ecommerce.userservice.dto.request.CreateAddressRequest;
import com.ecommerce.userservice.dto.request.UpdateAddressRequest;
import com.ecommerce.userservice.dto.response.AddressResponse;
import com.ecommerce.userservice.dto.response.ApiResponse;
import com.ecommerce.userservice.service.AddressService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/addresses")
public class AddressController {

    private final AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<AddressResponse>> createAddress(
            @RequestBody CreateAddressRequest request
    ) {
        AddressResponse response = addressService.createAddress(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>("Address created successfully", response));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<ApiResponse<List<AddressResponse>>> getAddressesByUserId(@PathVariable Long userId) {
        List<AddressResponse> response = addressService.getAddressesByUserId(userId);
        return ResponseEntity.ok(new ApiResponse<>("Addresses fetched successfully", response));
    }

    @GetMapping("/user/{userId}/{addressId}")
    public ResponseEntity<ApiResponse<AddressResponse>> getAddressById(
            @PathVariable Long userId,
            @PathVariable Long addressId
    ) {
        AddressResponse response = addressService.getAddressById(userId, addressId);
        return ResponseEntity.ok(new ApiResponse<>("Address fetched successfully", response));
    }

    @PutMapping("/user/{userId}/{addressId}")
    public ResponseEntity<ApiResponse<AddressResponse>> updateAddress(
            @PathVariable Long userId,
            @PathVariable Long addressId,
            @RequestBody UpdateAddressRequest request
    ) {
        AddressResponse response = addressService.updateAddress(userId, addressId, request);
        return ResponseEntity.ok(new ApiResponse<>("Address updated successfully", response));
    }

    @DeleteMapping("/user/{userId}/{addressId}")
    public ResponseEntity<ApiResponse<Object>> deleteAddress(
            @PathVariable Long userId,
            @PathVariable Long addressId
    ) {
        addressService.deleteAddress(userId, addressId);
        return ResponseEntity.ok(new ApiResponse<>("Address deleted successfully", null));
    }
}