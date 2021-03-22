package com.BoostingWebsite.order;

import com.BoostingWebsite.order.dto.OrderBoostDto;
import com.BoostingWebsite.utils.BaseFacade;

import java.util.List;

class OrderHistoryFacade extends BaseFacade {
    private final OrderCommandHandler orderCommandHandler;

    OrderHistoryFacade(OrderCommandHandler orderCommandHandler) {
        this.orderCommandHandler = orderCommandHandler;
    }

    List<OrderBoostDto> getCompletedOrderBoosts() {
        return orderCommandHandler.getCompletedOrderBoosts();
    }
}
