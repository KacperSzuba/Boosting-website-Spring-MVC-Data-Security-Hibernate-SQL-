package com.BoostingWebsite.order.preview;

import com.BoostingWebsite.account.user.ApplicationSession;
import com.BoostingWebsite.order.entity.OrderBoost;
import com.BoostingWebsite.order.repository.OrderBoostRepository;
import org.springframework.stereotype.Service;

@Service
public class OrderPreviewService {
    private final OrderBoostRepository orderBoostRepository;
    private final ApplicationSession applicationSession;

    public OrderPreviewService(OrderBoostRepository orderBoostRepository, ApplicationSession applicationSession) {
        this.orderBoostRepository = orderBoostRepository;
        this.applicationSession = applicationSession;
    }

    public OrderBoost getOrderBoost() throws OrderBoostNotFoundException {
        return orderBoostRepository
                .findActiveBoost(applicationSession.getActualUser())
                .orElseThrow(OrderBoostNotFoundException::new);
    }
}
