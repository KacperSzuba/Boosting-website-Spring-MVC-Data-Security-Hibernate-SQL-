package com.BoostingWebsite.order;

import com.BoostingWebsite.order.dto.OrderBoostDto;

import java.util.List;

class FreeOrdersFacade {
    private final OrderBoostBusiness orderBoostBusiness;

    FreeOrdersFacade(OrderBoostBusiness orderBoostBusiness) {
        this.orderBoostBusiness = orderBoostBusiness;
    }

    public List<OrderBoostDto> getFreeOrderBoosts(){
        return orderBoostBusiness.getFreeOrderBoosts();
    }
}
