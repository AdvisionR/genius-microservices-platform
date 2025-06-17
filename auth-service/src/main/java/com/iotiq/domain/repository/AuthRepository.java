package com.iotiq.domain.repository;

import com.iotiq.domain.model.Auth;

import java.util.Optional;

public interface AuthRepository {
    Auth save(Auth auth);
    Boolean existsByEmail(String email);
    Boolean existsByUserName(String userName);
    Optional<Auth> findByUserName(String userName);
}
