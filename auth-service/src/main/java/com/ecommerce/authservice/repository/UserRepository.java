package com.ecommerce.authservice.repository;

import com.ecommerce.authservice.entity.User;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);
    
    Optional<User> findByUsername(String username);
    
    Optional<User> findById(Long id);
}