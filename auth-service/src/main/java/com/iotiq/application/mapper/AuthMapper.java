package com.iotiq.application.mapper;

import com.iotiq.api.dto.RegisterRequestDTO;
import com.iotiq.api.dto.RegisterResponseDTO;
import com.iotiq.domain.model.Auth;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface AuthMapper {
    AuthMapper INSTANCE = Mappers.getMapper(AuthMapper.class);

    Auth toEntity(RegisterRequestDTO dto);

    RegisterResponseDTO toResponseDto(Auth auth);
}
