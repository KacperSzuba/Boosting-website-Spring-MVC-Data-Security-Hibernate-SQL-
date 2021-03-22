package com.BoostingWebsite.order.message;

import com.BoostingWebsite.account.SimpleUserDto;
import com.BoostingWebsite.order.OrderCommandHandler;
import com.BoostingWebsite.order.exception.OrderBoostNotFoundException;
import com.BoostingWebsite.order.message.dto.MessageDTO;

import java.util.List;

public class MessageCommandHandler {
    private final MessageBusiness messageBusiness;
    private final OrderCommandHandler orderCommandHandler;

    public MessageCommandHandler(MessageBusiness messageBusiness, OrderCommandHandler orderCommandHandler) {
        this.messageBusiness = messageBusiness;
        this.orderCommandHandler = orderCommandHandler;
    }

    public void save(Message message){
        messageBusiness.save(message);
    }

    public List<MessageDTO> getChatMessages(SimpleUserDto sender, SimpleUserDto recipient) throws OrderBoostNotFoundException {
        if(!orderCommandHandler.isActiveBoost()){
            throw new OrderBoostNotFoundException();
        }

        return messageBusiness.getChatMessages(sender, recipient);
    }

    public synchronized void save(MessageDTO messageDTO){
        messageBusiness.save(messageDTO);
    }
}
