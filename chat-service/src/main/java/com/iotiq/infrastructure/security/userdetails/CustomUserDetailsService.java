package com.iotiq.infrastructure.security.userdetails;

import com.iotiq.enums.UserRole;
import com.iotiq.infrastructure.security.CurrentUser;
import com.iotiq.security.jwt.JwtService;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private static final String ROLE_PREFIX = "ROLE_";
    private final CurrentUser currentUser;
    private final JwtService jwtService;

    @Override
    public UserDetails loadUserByUsername(String token) throws UsernameNotFoundException {
        if (!jwtService.isTokenValid(token)) {
            throw new UsernameNotFoundException("JWT Token is invalid");
        }

        String username = jwtService.extractSubject(token);
        Claims claims = jwtService.extractAllClaims(token);
        currentUser.setToken(token);
        currentUser.setRole(UserRole.valueOf(claims.get("role", String.class)));
        currentUser.setEmail(claims.get("email", String.class));
        currentUser.setUserName(username);

        List<SimpleGrantedAuthority> authorities = List.of(
                new SimpleGrantedAuthority(ROLE_PREFIX + currentUser.getRole())
        );

        return new CustomUserDetails(username, authorities);
    }
}
