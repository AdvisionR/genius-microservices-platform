package com.iotiq.unit.application.validator;

import com.iotiq.api.dto.RegisterRequestDTO;
import com.iotiq.application.validator.RegisterValidator;
import com.iotiq.domain.exception.PasswordNotMatchException;
import com.iotiq.domain.repository.AuthRepository;
import com.iotiq.exception.UserAlreadyExistsException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RegisterValidatorTest {
    @Mock
    private AuthRepository authRepository;

    @InjectMocks
    private RegisterValidator registerValidator;

    @Test
    void validate_shouldThrowPasswordNotMatchException_whenPasswordsDoesntMatch() {
        RegisterRequestDTO dto = new RegisterRequestDTO(
                "user",
                "pass1",
                "pass2",
                "email@example.com");

        assertThrows(PasswordNotMatchException.class, () -> registerValidator.validate(dto));
    }

    @Test
    void validate_shouldThrowUserAlreadyExistsException_whenEmailOrUserNameExists() {
        RegisterRequestDTO dto = new RegisterRequestDTO(
                "user",
                "pass",
                "pass",
                "email@example.com"
        );

        when(authRepository.existsByEmail(dto.email())).thenReturn(true);
        when(authRepository.existsByUserName(dto.userName())).thenReturn(false);

        assertThrows(UserAlreadyExistsException.class, () -> registerValidator.validate(dto));

        when(authRepository.existsByUserName(dto.userName())).thenReturn(true);
        when(authRepository.existsByEmail(dto.email())).thenReturn(false);

        assertThrows(UserAlreadyExistsException.class, () -> registerValidator.validate(dto));
    }

    @Test
    void validate_shouldPass_whenValidInput() {
        RegisterRequestDTO dto = new RegisterRequestDTO(
                "user",
                "pass",
                "pass",
                "email@example.com");

        assertDoesNotThrow(() -> registerValidator.validate(dto));
    }
}

