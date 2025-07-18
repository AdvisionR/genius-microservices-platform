package com.iotiq.infrastructure.config;

import com.iotiq.infrastructure.security.CurrentUser;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

@Configuration
@RequiredArgsConstructor
public class FeignClientConfig {
    private final CurrentUser currentUser;

    @Bean
    public RequestInterceptor requestInterceptor() {
        return new RequestInterceptor() {
            @Override
            public void apply(RequestTemplate template) {
                String token = currentUser.getToken();
                if (token != null && !token.isBlank()) {
                    if (StringUtils.hasText(token)) {
                        template.header("Authorization", "Bearer " + token);
                    }
                }
            }
        };
    }
}
