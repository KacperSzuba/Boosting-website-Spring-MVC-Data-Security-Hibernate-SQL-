package com.BoostingWebsite.order;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/order/orderHistory")
class OrderHistoryController {

    private final OrderBoostFacade orderBoostFacade;

    OrderHistoryController(final OrderBoostFacade orderBoostFacade) {
        this.orderBoostFacade = orderBoostFacade;
    }

    @GetMapping
    String orderPreviewPage(Model model) {
        model.addAttribute("orderHistory", orderBoostFacade.getCompletedOrderBoosts());
        return "order/orderHistory";
    }
}
