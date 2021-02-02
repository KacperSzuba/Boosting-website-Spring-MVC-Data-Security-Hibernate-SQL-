package com.BoostingWebsite.order.message;

import com.BoostingWebsite.account.User;
import com.BoostingWebsite.order.OrderBoostFacade;
import com.BoostingWebsite.order.exception.OrderBoostNotFoundException;
import com.BoostingWebsite.order.message.dto.MessageDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MessageFacade {
    private final MessageRepository messageRepository;
    private final OrderBoostFacade orderBoostFacade;
    private final MessageFactory messageFactory;

    public MessageFacade(MessageRepository messageRepository, OrderBoostFacade orderBoostFacade, MessageFactory messageFactory) {
        this.messageRepository = messageRepository;
        this.orderBoostFacade = orderBoostFacade;
        this.messageFactory = messageFactory;
    }

    public void save(Message message){
        messageRepository.save(message);
    }

    public List<MessageDTO> getChatMessages(User sender, User recipient) throws OrderBoostNotFoundException {
        if(!orderBoostFacade.isActiveBoost()){
            throw new OrderBoostNotFoundException();
        }

        return messageRepository.getChatMessages(sender.getId(), recipient.getId()).stream()
                .map(Message::toDto)
                .collect(Collectors.toList());
    }

    public synchronized void saveMessage(MessageDTO messageDTO){
        messageRepository.save(messageFactory.from(messageDTO));
    }

}
