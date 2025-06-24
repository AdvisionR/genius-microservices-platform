package com.iotiq.infrastructure.client;

import com.iotiq.api.dto.ApiResponse;
import com.iotiq.api.dto.CreateUserRequestDTO;
import com.iotiq.dto.UserProfileResponseDTO;
import com.iotiq.infrastructure.config.FeignClientConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.iotiq.infrastructure.config.RestApis.CREATE_USER;
import static com.iotiq.infrastructure.config.RestApis.GET_USER;

@FeignClient(
        name="user-service",
        url="${service.user.url}",
        configuration = FeignClientConfig.class
)
public interface UserClient {
    @PostMapping(CREATE_USER)
    ResponseEntity<ApiResponse<UserProfileResponseDTO>> createUser(@RequestBody CreateUserRequestDTO dto);

    @GetMapping(GET_USER)
    ResponseEntity<ApiResponse<UserProfileResponseDTO>> getUser();
}
