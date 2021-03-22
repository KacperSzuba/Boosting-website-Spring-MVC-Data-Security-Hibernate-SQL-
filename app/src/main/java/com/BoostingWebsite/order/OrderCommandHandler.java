package com.BoostingWebsite.order;

import com.BoostingWebsite.account.SimpleUserDto;
import com.BoostingWebsite.order.dto.OrderBoostDto;
import com.BoostingWebsite.order.exception.OrderBoostNotFoundException;

import java.util.List;

public class OrderCommandHandler {
    private final OrderBoostBusiness orderBoostBusiness;
    private final OrderExtrasBusiness orderExtrasBusiness;

    OrderCommandHandler(OrderBoostBusiness orderBoostBusiness, OrderExtrasBusiness orderExtrasBusiness) {
        this.orderBoostBusiness = orderBoostBusiness;
        this.orderExtrasBusiness = orderExtrasBusiness;
    }

    public void makeOrder(OrderBoost orderBoost, SimpleUserDto userDto) {
        orderBoostBusiness.makeOrder(orderBoost, userDto);
    }

    public boolean whetherUserHasOrder(){
        return orderBoostBusiness.whetherUserHasOrder();
    }

    public List<OrderBoostDto> getFreeOrderBoosts(){
        return orderBoostBusiness.getFreeOrderBoosts();
    }

    public List<OrderBoostDto> getCompletedOrderBoosts(){
        return orderBoostBusiness.getCompletedOrderBoosts();
    }

    public OrderBoostDto findActiveBoost() throws OrderBoostNotFoundException {
        return orderBoostBusiness.findActiveBoost();
    }

    public boolean isActiveBoost(){
        return orderBoostBusiness.isActiveBoost();
    }

    public List<OrderExtras> getOrderExtras(){
        return orderExtrasBusiness.getOrderExtras();
    }
}
