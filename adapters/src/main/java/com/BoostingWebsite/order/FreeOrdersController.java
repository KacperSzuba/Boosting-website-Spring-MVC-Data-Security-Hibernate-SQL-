package com.BoostingWebsite.order;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/order/freeOrders")
class FreeOrdersController {

    private final OrderBoostFacade orderBoostFacade;

    FreeOrdersController(final OrderBoostFacade orderBoostFacade) {
        this.orderBoostFacade = orderBoostFacade;
    }

    @GetMapping
    String orderPreviewPage(Model model) {
        model.addAttribute("freeOrders", orderBoostFacade.getFreeOrderBoosts());

        return "order/freeOrders";
    }
}
