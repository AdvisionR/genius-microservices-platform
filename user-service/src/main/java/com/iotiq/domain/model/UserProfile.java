package com.iotiq.domain.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserProfile {
    @Id
    @GeneratedValue
    @Column(updatable = false, nullable = false)
    private UUID uuid;

    @Column(updatable = false, nullable = false)
    private UUID authUuid;

    @Column(nullable = false, unique = true, length = 50)
    private String userName;

    private String name;

    @Email
    @Column(nullable = false, unique = true)
    private String email;

    private String phone;

    private String avatar;

    private Boolean isActive;

    @Column(nullable = false, updatable = false)
    private Instant createdAt;

    @PrePersist
    public void onCreate() {
        this.createdAt = Instant.now();
        if (this.isActive == null) {
            this.isActive = false;
        }
    }
}
