package com.iotiq.application.mapper;

import com.iotiq.api.dto.RegisterRequestDTO;
import com.iotiq.api.dto.RegisterResponseDTO;
import com.iotiq.domain.model.Auth;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuthMapper {
    Auth toEntity(RegisterRequestDTO dto);
    RegisterResponseDTO toResponseDto(Auth auth);
}
