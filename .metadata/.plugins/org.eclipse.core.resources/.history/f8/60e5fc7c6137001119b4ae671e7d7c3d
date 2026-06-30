package com.ecommerce.adminservice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.adminservice.entity.Admin;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {

    boolean existsByUserId(Long userId);

    boolean existsByUsername(String username);

    Optional<Admin> findByUserId(Long userId);
}