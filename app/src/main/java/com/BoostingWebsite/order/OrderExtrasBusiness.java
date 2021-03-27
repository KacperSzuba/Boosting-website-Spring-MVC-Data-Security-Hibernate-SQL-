package com.BoostingWebsite.order;

import java.util.List;

class OrderExtrasBusiness {
    private final OrderExtrasRepository orderExtrasRepository;

    OrderExtrasBusiness(OrderExtrasRepository orderExtrasRepository) {
        this.orderExtrasRepository = orderExtrasRepository;
    }

    public List<OrderExtras> getOrderExtras(){
        return orderExtrasRepository.findAll();
    }
}
