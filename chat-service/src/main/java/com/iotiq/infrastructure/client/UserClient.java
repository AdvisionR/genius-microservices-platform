package com.iotiq.infrastructure.client;

import com.iotiq.api.dto.ApiResponse;
import com.iotiq.dto.UserProfileResponseDTO;
import com.iotiq.infrastructure.config.FeignClientConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import static com.iotiq.infrastructure.config.RestApis.GET_USER;

@FeignClient(
        name="user-service",
        url="${service.user.url}",
        configuration = FeignClientConfig.class
)
public interface UserClient {
    @GetMapping(GET_USER)
    ResponseEntity<ApiResponse<UserProfileResponseDTO>> getUser();
}
