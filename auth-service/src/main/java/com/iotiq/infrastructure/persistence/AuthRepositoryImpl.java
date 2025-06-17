package com.iotiq.infrastructure.persistence;

import com.iotiq.domain.model.Auth;
import com.iotiq.domain.repository.AuthRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class AuthRepositoryImpl implements AuthRepository {
    private final JpaAuthRepository jpaAuthRepository;

    @Override
    public Auth save(Auth auth) {
        return jpaAuthRepository.save(auth);
    }

    @Override
    public Boolean existsByEmail(String email) {
        return jpaAuthRepository.existsByEmail(email);
    }

    @Override
    public Boolean existsByUserName(String userName) {
        return jpaAuthRepository.existsByUserName(userName);
    }

    @Override
    public Optional<Auth> findByUserName(String userName) {
        return jpaAuthRepository.findByUserName(userName);
    }
}
