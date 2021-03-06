package com.BoostingWebsite.order.message;

import com.BoostingWebsite.account.SimpleUserDto;
import com.BoostingWebsite.account.UserQueryRepository;
import com.BoostingWebsite.order.message.dto.MessageDTO;
import org.springframework.stereotype.Service;

@Service
class MessageFactory {
    private final UserQueryRepository userQueryRepository;

    MessageFactory(final UserQueryRepository userQueryRepository) {
        this.userQueryRepository = userQueryRepository;
    }

    Message from(MessageDTO messageDTO){
        SimpleUserDto sender = userQueryRepository.findByUsername(messageDTO.getSenderName());
        SimpleUserDto recipient = userQueryRepository.findByUsername(messageDTO.getRecipientName());

        return new Message(messageDTO.getContent(), sender, recipient);
    }
}
