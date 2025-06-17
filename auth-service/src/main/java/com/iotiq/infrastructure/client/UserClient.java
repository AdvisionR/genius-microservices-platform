package com.iotiq.infrastructure.client;

import com.iotiq.api.dto.ApiResponse;
import com.iotiq.api.dto.CreateUserRequestDTO;
import com.iotiq.dto.UserProfileResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import static com.iotiq.infrastructure.config.RestApis.CREATE_USER;

@FeignClient(name="user-service", url="${service.user.url}")
public interface UserClient {
    @PostMapping(CREATE_USER)
    ResponseEntity<ApiResponse<UserProfileResponseDTO>> createUser(@RequestBody CreateUserRequestDTO dto);
}
