package com.BoostingWebsite.order;

import com.BoostingWebsite.account.AccountCommandHandler;
import com.BoostingWebsite.utils.BaseFacade;

import java.util.List;

class OrderFacade extends BaseFacade {
    private final OrderCommandHandler orderCommandHandler;
    private final AccountCommandHandler accountCommandHandler;

    OrderFacade(OrderCommandHandler orderCommandHandler, AccountCommandHandler accountCommandHandler) {
        this.orderCommandHandler = orderCommandHandler;
        this.accountCommandHandler = accountCommandHandler;
    }

    List<OrderExtras> getOrderExtras() {
        return orderCommandHandler.getOrderExtras();
    }

    void makeOrder(OrderBoost orderBoost) {
        orderCommandHandler.makeOrder(orderBoost, accountCommandHandler.findSimpleUserDtoById(applicationSession.getContext().getUser().getId()));
    }

    boolean whetherUserHasOrder() {
        return orderCommandHandler.whetherUserHasOrder();
    }
}
