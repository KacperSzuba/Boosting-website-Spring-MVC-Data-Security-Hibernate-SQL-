package com.BoostingWebsite.order.message;

import com.BoostingWebsite.account.SimpleUserDto;
import com.BoostingWebsite.order.OrderBoostBusiness;
import com.BoostingWebsite.order.exception.OrderBoostNotFoundException;
import com.BoostingWebsite.order.message.dto.MessageDTO;
import com.BoostingWebsite.utils.BaseFacade;

import java.util.List;
import java.util.stream.Collectors;

public class MessageBusiness extends BaseFacade {
    private final MessageRepository messageRepository;
    private final OrderBoostBusiness orderBoostBusiness;
    private final MessageFactory messageFactory;

    public MessageBusiness(final MessageRepository messageRepository, final OrderBoostBusiness orderBoostBusiness, final MessageFactory messageFactory) {
        this.messageRepository = messageRepository;
        this.orderBoostBusiness = orderBoostBusiness;
        this.messageFactory = messageFactory;
    }

    public void save(Message message){
        messageRepository.save(message);
    }

    public List<MessageDTO> getChatMessages(SimpleUserDto sender, SimpleUserDto recipient) throws OrderBoostNotFoundException {
        if(!orderBoostBusiness.isActiveBoost()){
            throw new OrderBoostNotFoundException();
        }

        return messageRepository.getChatMessages(sender.getSnapshot().getId(), recipient.getSnapshot().getId()).stream()
                .map(messageFactory::toDto)
                .collect(Collectors.toList());
    }

    public synchronized void save(MessageDTO messageDTO){
        messageRepository.save(messageFactory.from(messageDTO));
    }
}
