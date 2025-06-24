package com.iotiq.application.service;

import com.iotiq.application.mapper.UserMapper;
import com.iotiq.application.usecase.GetUserUseCase;
import com.iotiq.domain.model.UserProfile;
import com.iotiq.domain.repository.UserRepository;
import com.iotiq.dto.UserProfileResponseDTO;
import com.iotiq.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class GetUserService implements GetUserUseCase {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public UserProfileResponseDTO getUserByUserName(String userName) {
        UserProfile userProfile = userRepository.findByUserName(userName)
                .orElseThrow(UserNotFoundException::new);

        return userMapper.toResponseDto(userProfile);
    }
}
