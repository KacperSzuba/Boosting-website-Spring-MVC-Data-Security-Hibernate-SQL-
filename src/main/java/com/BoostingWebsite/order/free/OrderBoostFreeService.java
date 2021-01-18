package com.BoostingWebsite.order.free;

import com.BoostingWebsite.order.entity.OrderBoost;
import com.BoostingWebsite.order.repository.OrderBoostRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
class OrderBoostFreeService {

    private final OrderBoostRepository orderBoostRepository;
    OrderBoostFreeService(OrderBoostRepository orderBoostRepository) {
        this.orderBoostRepository = orderBoostRepository;
    }

    List<OrderBoost> getFreeOrderBoosts(){
       return orderBoostRepository.getFreeOrderBoosts();
    }

}
