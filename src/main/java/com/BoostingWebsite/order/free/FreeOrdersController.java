package com.BoostingWebsite.order.free;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/order/freeOrders")
class FreeOrdersController {

    @GetMapping
    String orderPreviewPage() {
        return "order/freeOrders";
    }
}
