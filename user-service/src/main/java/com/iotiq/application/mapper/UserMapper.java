package com.iotiq.application.mapper;

import com.iotiq.dto.UserProfileResponseDTO;
import com.iotiq.domain.model.UserProfile;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserProfileResponseDTO toResponseDto(UserProfile userProfile);
}
