package com.ecommerce.userservice.service;

import com.ecommerce.userservice.dto.request.CreateAddressRequest;
import com.ecommerce.userservice.dto.request.UpdateAddressRequest;
import com.ecommerce.userservice.dto.response.AddressResponse;
import com.ecommerce.userservice.entity.Address;
import com.ecommerce.userservice.exception.ResourceNotFoundException;
import com.ecommerce.userservice.repository.AddressRepository;
import com.ecommerce.userservice.repository.UserProfileRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AddressService {

    private final AddressRepository addressRepository;
    private final UserProfileRepository userProfileRepository;

    public AddressService(AddressRepository addressRepository,
                          UserProfileRepository userProfileRepository) {
        this.addressRepository = addressRepository;
        this.userProfileRepository = userProfileRepository;
    }

    public AddressResponse createAddress(CreateAddressRequest request) {
        userProfileRepository.findByUserId(request.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Cannot create address. Profile not found for userId: " + request.getUserId()
                ));

        if (Boolean.TRUE.equals(request.getIsDefault())) {
            clearExistingDefault(request.getUserId());
        }

        Address address = new Address();
        address.setUserId(request.getUserId());
        address.setLabel(request.getLabel() != null ? request.getLabel() : "Home");
        address.setAddressLine(request.getAddressLine());
        address.setCity(request.getCity());
        address.setProvince(request.getProvince());
        address.setPostalCode(request.getPostalCode());
        address.setIsDefault(Boolean.TRUE.equals(request.getIsDefault()));

        Address saved = addressRepository.save(address);
        return mapToResponse(saved);
    }

    @Transactional(readOnly = true)
    public List<AddressResponse> getAddressesByUserId(Long userId) {
        return addressRepository.findByUserId(userId)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    public AddressResponse getAddressById(Long userId, Long addressId) {
        Address address = addressRepository.findByIdAndUserId(addressId, userId)
                .orElseThrow(() -> new ResourceNotFoundException("Address not found"));

        return mapToResponse(address);
    }

    public AddressResponse updateAddress(Long userId, Long addressId, UpdateAddressRequest request) {
        Address address = addressRepository.findByIdAndUserId(addressId, userId)
                .orElseThrow(() -> new ResourceNotFoundException("Address not found"));

        if (request.getLabel() != null) {
            address.setLabel(request.getLabel());
        }
        if (request.getAddressLine() != null) {
            address.setAddressLine(request.getAddressLine());
        }
        if (request.getCity() != null) {
            address.setCity(request.getCity());
        }
        if (request.getProvince() != null) {
            address.setProvince(request.getProvince());
        }
        if (request.getPostalCode() != null) {
            address.setPostalCode(request.getPostalCode());
        }
        if (request.getIsDefault() != null) {
            if (Boolean.TRUE.equals(request.getIsDefault())) {
                clearExistingDefault(userId);
            }
            address.setIsDefault(request.getIsDefault());
        }

        Address updated = addressRepository.save(address);
        return mapToResponse(updated);
    }

    public void deleteAddress(Long userId, Long addressId) {
        Address address = addressRepository.findByIdAndUserId(addressId, userId)
                .orElseThrow(() -> new ResourceNotFoundException("Address not found"));

        addressRepository.delete(address);
    }

    private void clearExistingDefault(Long userId) {
        addressRepository.findByUserIdAndIsDefaultTrue(userId)
                .ifPresent(existingDefault -> {
                    existingDefault.setIsDefault(false);
                    addressRepository.save(existingDefault);
                });
    }

    private AddressResponse mapToResponse(Address address) {
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