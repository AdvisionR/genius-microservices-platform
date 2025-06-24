package com.iotiq.infrastructure.persistence;

import com.iotiq.domain.model.UserProfile;
import com.iotiq.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {
    private final JpaUserRepository jpaUserRepository;

    @Override
    public UserProfile save(UserProfile user) {
        return jpaUserRepository.save(user);
    }

    @Override
    public Boolean existsByEmail(String email) {
        return jpaUserRepository.existsByEmail(email);
    }

    @Override
    public Boolean existsByUserName(String userName) {
        return jpaUserRepository.existsByUserName(userName);
    }

    @Override
    public Optional<UserProfile> findByUserName(String userName) {
        return jpaUserRepository.findByUserName(userName);
    }
}
