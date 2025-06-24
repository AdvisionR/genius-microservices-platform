package com.iotiq.infrastructure.persistence;

import com.iotiq.domain.model.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface JpaUserRepository extends JpaRepository<UserProfile, UUID> {
    Boolean existsByEmail(String email);
    Boolean existsByUserName(String userName);
    Optional<UserProfile> findByUserName(String userName);
}
