package com.BoostingWebsite.order.message;

import com.BoostingWebsite.account.SimpleUserDto;
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
        SimpleUserDto sender = userFacade.findUserByUsername(messageDTO.getSenderName());
        SimpleUserDto recipient = userFacade.findUserByUsername(messageDTO.getRecipientName());

        return new Message(messageDTO.getContent(), sender, recipient);
    }

}
