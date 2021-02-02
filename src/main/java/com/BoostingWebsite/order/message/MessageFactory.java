package com.BoostingWebsite.order.message;

import com.BoostingWebsite.account.User;
import com.BoostingWebsite.account.UserFacade;
import com.BoostingWebsite.order.message.dto.MessageDTO;
import org.springframework.stereotype.Service;

@Service
class MessageFactory {
    private final UserFacade userFacade;

    MessageFactory(UserFacade userFacade) {
        this.userFacade = userFacade;
    }

    Message from(MessageDTO messageDTO){
        User sender = userFacade.findByUsername(messageDTO.getSenderName());
        User recipient = userFacade.findByUsername(messageDTO.getRecipientName());

        return new Message(messageDTO.getContent(), sender, recipient);
    }

}
