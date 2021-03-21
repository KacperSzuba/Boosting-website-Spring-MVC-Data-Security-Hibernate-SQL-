package com.BoostingWebsite.order;

import com.BoostingWebsite.order.dto.OrderBoostDto;

import java.util.List;

class OrderHistoryFacade {
    private final OrderBoostBusiness orderBoostBusiness;

    OrderHistoryFacade(OrderBoostBusiness orderBoostBusiness) {
        this.orderBoostBusiness = orderBoostBusiness;
    }

    List<OrderBoostDto> getCompletedOrderBoosts() {
        return orderBoostBusiness.getCompletedOrderBoosts();
    }
}
