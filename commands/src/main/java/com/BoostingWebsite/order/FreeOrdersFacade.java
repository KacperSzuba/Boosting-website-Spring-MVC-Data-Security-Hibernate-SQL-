package com.BoostingWebsite.order;

import com.BoostingWebsite.order.dto.OrderBoostDto;
import com.BoostingWebsite.utils.BaseFacade;

import java.util.List;

class FreeOrdersFacade extends BaseFacade {
    private final OrderBoostBusiness orderBoostBusiness;

    FreeOrdersFacade(OrderBoostBusiness orderBoostBusiness) {
        this.orderBoostBusiness = orderBoostBusiness;
    }

    public List<OrderBoostDto> getFreeOrderBoosts(){
        return orderBoostBusiness.getFreeOrderBoosts();
    }
}
