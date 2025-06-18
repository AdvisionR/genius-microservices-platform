package com.iotiq.application.validator;

import com.iotiq.api.dto.RegisterRequestDTO;
import com.iotiq.domain.exception.PasswordNotMatchException;
import com.iotiq.domain.repository.AuthRepository;
import com.iotiq.exception.UserAlreadyExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RegisterValidator {
    private final AuthRepository authRepository;

    public void validate(RegisterRequestDTO dto) {
        if (!dto.getPassword().equals(dto.getRePassword()))
            throw new PasswordNotMatchException();

        if (authRepository.existsByEmail(dto.getEmail()) || authRepository.existsByUserName(dto.getUserName()))
            throw new UserAlreadyExistsException(dto.getEmail(), dto.getUserName());
    }
}
