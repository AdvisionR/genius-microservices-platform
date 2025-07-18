package com.iotiq.infrastructure.persistence;

import com.iotiq.domain.model.Chat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface JpaChatRepository extends JpaRepository<Chat, UUID> {
    List<Chat> findAllByUserUuidOrderByCreatedAtDesc(UUID userUuid);
}
