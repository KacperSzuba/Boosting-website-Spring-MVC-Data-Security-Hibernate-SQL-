package com.BoostingWebsite.order.message;

import com.BoostingWebsite.account.SimpleUserDto;
import com.BoostingWebsite.order.exception.OrderBoostNotFoundException;
import com.BoostingWebsite.order.message.dto.MessageDTO;
import com.BoostingWebsite.utils.BaseBusiness;

import java.util.List;
import java.util.stream.Collectors;

class MessageBusiness extends BaseBusiness {
    private final MessageRepository messageRepository;
    private final MessageFactory messageFactory;

    MessageBusiness(final MessageRepository messageRepository, final MessageFactory messageFactory) {
        this.messageRepository = messageRepository;
        this.messageFactory = messageFactory;
    }

    void save(Message message){
        messageRepository.save(message);
    }

    List<MessageDTO> getChatMessages(SimpleUserDto sender, SimpleUserDto recipient) throws OrderBoostNotFoundException {
        return messageRepository.getChatMessages(sender.getSnapshot().getId(), recipient.getSnapshot().getId()).stream()
                .map(messageFactory::toDto)
                .collect(Collectors.toList());
    }

    synchronized void save(MessageDTO messageDTO){
        messageRepository.save(messageFactory.from(messageDTO));
    }
}
