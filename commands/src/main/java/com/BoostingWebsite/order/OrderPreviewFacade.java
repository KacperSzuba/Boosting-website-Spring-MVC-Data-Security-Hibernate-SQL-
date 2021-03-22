package com.BoostingWebsite.order;

import com.BoostingWebsite.account.SimpleUserDto;
import com.BoostingWebsite.api.APICommandHandler;
import com.BoostingWebsite.order.dto.OrderBoostDto;
import com.BoostingWebsite.order.exception.OrderBoostNotFoundException;
import com.BoostingWebsite.order.message.MessageCommandHandler;
import com.BoostingWebsite.order.message.dto.MessageDTO;
import com.BoostingWebsite.utils.BaseFacade;

import java.io.IOException;
import java.util.List;

class OrderPreviewFacade extends BaseFacade {
    private final OrderCommandHandler orderCommandHandler;
    private final MessageCommandHandler messageCommandHandler;
    private final APICommandHandler apiCommandHandler;

    OrderPreviewFacade(OrderCommandHandler orderCommandHandler, MessageCommandHandler messageCommandHandler, APICommandHandler apiCommandHandler) {
        this.orderCommandHandler = orderCommandHandler;
        this.messageCommandHandler = messageCommandHandler;
        this.apiCommandHandler = apiCommandHandler;
    }

    OrderBoostDto findActiveBoost() throws OrderBoostNotFoundException {
        return orderCommandHandler.findActiveBoost();
    }

    LeagueDto getCurrentLeague() throws IOException {
        return apiCommandHandler.getCurrentLeague();
    }

    List<MessageDTO> getChatMessages(SimpleUserDto sender, SimpleUserDto recipient) throws OrderBoostNotFoundException {
        return messageCommandHandler.getChatMessages(sender, recipient);
    }


    void saveMessage(MessageDTO messageDTO) {
        messageCommandHandler.save(messageDTO);
    }
}
