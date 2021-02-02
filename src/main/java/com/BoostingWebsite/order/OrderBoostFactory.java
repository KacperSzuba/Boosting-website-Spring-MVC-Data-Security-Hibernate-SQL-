package com.BoostingWebsite.order;

import com.BoostingWebsite.order.dto.OrderBoostDto;
import org.springframework.stereotype.Service;

@Service
class OrderBoostFactory {
    OrderBoost from(final OrderBoostDto source){
        OrderBoost result = new OrderBoost();


        return result;
    }
}
