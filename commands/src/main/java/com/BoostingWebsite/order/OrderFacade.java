package com.BoostingWebsite.order;

import java.util.List;

class OrderFacade {
    private final OrderBoostBusiness orderBoostBusiness;
    private final OrderExtrasBusiness orderExtrasBusiness;
    OrderFacade(OrderBoostBusiness orderBoostBusiness, OrderExtrasBusiness orderExtrasBusiness) {
        this.orderBoostBusiness = orderBoostBusiness;
        this.orderExtrasBusiness = orderExtrasBusiness;
    }

    List<OrderExtras> getOrderExtras() {
        return orderExtrasBusiness.getOrderExtras();
    }

    void makeOrder(OrderBoost orderBoost) {
        orderBoostBusiness.makeOrder(orderBoost);
    }

    boolean whetherUserHasOrder() {
        return orderBoostBusiness.whetherUserHasOrder();
    }
}
