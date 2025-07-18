package com.iotiq.domain.repository;

import com.iotiq.domain.model.Message;

public interface MessageRepository {
    Message save(Message message);
}
