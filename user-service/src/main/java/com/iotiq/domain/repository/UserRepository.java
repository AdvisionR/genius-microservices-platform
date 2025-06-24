package com.iotiq.domain.repository;

import com.iotiq.domain.model.UserProfile;

import java.util.Optional;

public interface UserRepository {
    UserProfile save(UserProfile user);
    Boolean existsByEmail(String email);
    Boolean existsByUserName(String userName);
    Optional<UserProfile> findByUserName(String userName);
}
