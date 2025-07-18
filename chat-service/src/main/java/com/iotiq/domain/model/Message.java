package com.iotiq.domain.model;

import jakarta.persistence.*;
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
public class Message {
    @Id
    @GeneratedValue
    @Column(updatable = false, nullable = false)
    private UUID uuid;

    private Sender sender;

    @Column(columnDefinition = "TEXT")
    private String content;

    private Instant createdAt;

    @ManyToOne
    private Chat chat;

    @PrePersist
    public void onCreate() {
        this.createdAt = Instant.now();
    }
}
