package com.BoostingWebsite.order.message;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
class MessageRepositoryImpl implements MessageRepository {
    private final SqlMessageRepository repository;

    MessageRepositoryImpl(SqlMessageRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Message> list(Long id) {
        return repository.list(id).stream().map(Message::restore).collect(Collectors.toList());
    }

    @Override
    public List<Message> getChatMessages(Long senderId, Long recipientId) {
        return repository.getChatMessages(senderId, recipientId).stream().map(Message::restore).collect(Collectors.toList());
    }

    @Override
    public Message save(Message message) {
        return Message.restore(repository.save(message.getSnapshot()));
    }
}
