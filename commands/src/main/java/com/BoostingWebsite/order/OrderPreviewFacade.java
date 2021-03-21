package com.BoostingWebsite.order;

import com.BoostingWebsite.account.SimpleUserDto;
import com.BoostingWebsite.api.LeagueOfLegendsAPIBusiness;
import com.BoostingWebsite.order.dto.OrderBoostDto;
import com.BoostingWebsite.order.exception.OrderBoostNotFoundException;
import com.BoostingWebsite.order.message.MessageBusiness;
import com.BoostingWebsite.order.message.dto.MessageDTO;

import java.io.IOException;
import java.util.List;

class OrderPreviewFacade {
    private final OrderBoostBusiness orderBoostBusiness;
    private final LeagueOfLegendsAPIBusiness leagueOfLegendsAPIBusiness;
    private final MessageBusiness messageBusiness;
    public OrderPreviewFacade(
            final OrderBoostBusiness orderBoostBusiness,
            final LeagueOfLegendsAPIBusiness leagueOfLegendsAPIBusiness,
            final MessageBusiness messageBusiness) {
        this.orderBoostBusiness = orderBoostBusiness;
        this.leagueOfLegendsAPIBusiness = leagueOfLegendsAPIBusiness;
        this.messageBusiness = messageBusiness;
    }

    OrderBoostDto findActiveBoost() throws OrderBoostNotFoundException {
        return orderBoostBusiness.findActiveBoost();
    }

    LeagueDto getCurrentLeague() throws IOException {
        return leagueOfLegendsAPIBusiness.getCurrentLeague();
    }

    List<MessageDTO> getChatMessages(SimpleUserDto sender, SimpleUserDto recipient) throws OrderBoostNotFoundException {
        return messageBusiness.getChatMessages(sender, recipient);
    }


    void saveMessage(MessageDTO messageDTO) {
        messageBusiness.save(messageDTO);
    }
}
