package com.BoostingWebsite.order.message;

import com.BoostingWebsite.account.SimpleUserDto;
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

    public MessageFacade(final MessageRepository messageRepository, final OrderBoostFacade orderBoostFacade, final MessageFactory messageFactory) {
        this.messageRepository = messageRepository;
        this.orderBoostFacade = orderBoostFacade;
        this.messageFactory = messageFactory;
    }

    public void save(Message message){
        messageRepository.save(message);
    }

    public List<MessageDTO> getChatMessages(SimpleUserDto sender, SimpleUserDto recipient) throws OrderBoostNotFoundException {
        if(!orderBoostFacade.isActiveBoost()){
            throw new OrderBoostNotFoundException();
        }

        return messageRepository.getChatMessages(sender.getId(), recipient.getId()).stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public synchronized void saveMessage(MessageDTO messageDTO){
        messageRepository.save(messageFactory.from(messageDTO));
    }

    MessageDTO toDto(Message message){
        return MessageDTO.builder()
                .withSenderName(message.getAuthor().getUsername())
                .withRecipientName(message.getRecipient().getUsername())
                .withContent(message.getMessage())
                .build();
    }
}
