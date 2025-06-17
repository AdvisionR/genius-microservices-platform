package com.iotiq.infrastructure.persistence;

import com.iotiq.domain.model.Auth;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface JpaAuthRepository extends JpaRepository<Auth, UUID> {
    Boolean existsByEmail(String email);
    Boolean existsByUserName(String userName);
    Optional<Auth> findByUserName(String userName);
}
