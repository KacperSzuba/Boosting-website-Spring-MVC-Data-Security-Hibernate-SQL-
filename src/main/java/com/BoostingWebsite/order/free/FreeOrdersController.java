package com.BoostingWebsite.order.free;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/order/freeOrders")
class FreeOrdersController {

    private final OrderBoostFreeService orderBoostFreeService;

    FreeOrdersController(OrderBoostFreeService orderBoostFreeService) {
        this.orderBoostFreeService = orderBoostFreeService;
    }

    @GetMapping
    String orderPreviewPage(Model model) {
        model.addAttribute("freeOrders", orderBoostFreeService.getFreeOrderBoosts());

        return "order/freeOrders";
    }
}
