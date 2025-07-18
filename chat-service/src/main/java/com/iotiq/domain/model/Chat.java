package com.iotiq.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.core.annotation.Order;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Chat {
    @Id
    @GeneratedValue
    @Column(updatable = false, nullable = false)
    private UUID uuid;

    private String title;

    private UUID userUuid;

    private Instant createdAt;

    @OneToMany(mappedBy = "chat", cascade = CascadeType.ALL)
    @OrderBy("createdAt DESC")
    private List<Message> messages = new ArrayList<>();

    @PrePersist
    public void onCreate() {
        this.createdAt = Instant.now();
    }
}
