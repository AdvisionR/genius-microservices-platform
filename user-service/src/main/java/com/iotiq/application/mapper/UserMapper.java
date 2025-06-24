package com.iotiq.application.mapper;

import com.iotiq.dto.UserProfileResponseDTO;
import com.iotiq.domain.model.UserProfile;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface UserMapper {
    UserProfileResponseDTO toResponseDto(UserProfile userProfile);
}
