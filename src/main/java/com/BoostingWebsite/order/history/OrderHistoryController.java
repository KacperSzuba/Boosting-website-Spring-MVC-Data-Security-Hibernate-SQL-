package com.BoostingWebsite.order.history;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/order/orderHistory")
class OrderHistoryController {

    @GetMapping
    String orderPreviewPage() {
        return "order/orderHistory";
    }
}
