package com.iotiq.application.service;

import com.iotiq.api.dto.ApiResponse;
import com.iotiq.application.usecase.MeUseCase;
import com.iotiq.dto.UserDTO;
import com.iotiq.dto.UserProfileResponseDTO;
import com.iotiq.enums.ServiceName;
import com.iotiq.exception.ServiceUnavailableException;
import com.iotiq.exception.UserNotFoundException;
import com.iotiq.infrastructure.client.UserClient;
import com.iotiq.infrastructure.security.CurrentUser;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MeService implements MeUseCase {
    private final CurrentUser currentUser;
    private final UserClient userClient;

    @Override
    public UserDTO getCurrentUser() {
        ResponseEntity<ApiResponse<UserProfileResponseDTO>> response = userClient.getUser();

        if (!response.getStatusCode().is2xxSuccessful()) {
            throw new ServiceUnavailableException(ServiceName.USER);
        }

        UserProfileResponseDTO userProfile = Optional.ofNullable(response.getBody())
                .map(ApiResponse::data)
                .orElseThrow(UserNotFoundException::new);

        return UserDTO.builder()
                .userName(currentUser.getUserName())
                .email(currentUser.getEmail())
                .role(currentUser.getRole())
                .uuid(userProfile.getUuid())
                .authUuid(userProfile.getAuthUuid())
                .name(userProfile.getName())
                .phone(userProfile.getPhone())
                .avatar(userProfile.getAvatar())
                .build();
    }
}
