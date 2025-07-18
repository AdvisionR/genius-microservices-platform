package com.iotiq.infrastructure.persistence;

import com.iotiq.domain.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JpaMessageRepository extends JpaRepository<Message, UUID> {
}
