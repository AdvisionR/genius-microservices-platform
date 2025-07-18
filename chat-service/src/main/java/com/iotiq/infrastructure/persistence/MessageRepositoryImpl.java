package com.iotiq.infrastructure.persistence;

import com.iotiq.domain.model.Message;
import com.iotiq.domain.repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class MessageRepositoryImpl implements MessageRepository {
    private final JpaMessageRepository jpaMessageRepository;

    @Override
    public Message save(Message message) {
        return jpaMessageRepository.save(message);
    }
}
