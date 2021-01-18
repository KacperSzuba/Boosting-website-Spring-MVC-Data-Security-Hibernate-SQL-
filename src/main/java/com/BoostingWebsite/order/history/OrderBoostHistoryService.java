package com.BoostingWebsite.order.history;

import com.BoostingWebsite.account.user.ApplicationSession;
import com.BoostingWebsite.order.entity.OrderBoost;
import com.BoostingWebsite.order.repository.OrderBoostRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
class OrderBoostHistoryService {

    private final OrderBoostRepository orderBoostRepository;
    private final ApplicationSession applicationSession;
    OrderBoostHistoryService(OrderBoostRepository orderBoostRepository, ApplicationSession applicationSession) {
        this.orderBoostRepository = orderBoostRepository;
        this.applicationSession = applicationSession;
    }

    List<OrderBoost> getCompletedOrderBoosts(){
        return orderBoostRepository.findDoneOrderBoost(applicationSession.getActualUser());
    }
}
