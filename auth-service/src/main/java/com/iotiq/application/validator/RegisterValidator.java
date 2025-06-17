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
        if (!dto.password().equals(dto.rePassword()))
            throw new PasswordNotMatchException();

        if (authRepository.existsByEmail(dto.email()) || authRepository.existsByUserName(dto.userName()))
            throw new UserAlreadyExistsException(dto.email(), dto.userName());
    }
}
