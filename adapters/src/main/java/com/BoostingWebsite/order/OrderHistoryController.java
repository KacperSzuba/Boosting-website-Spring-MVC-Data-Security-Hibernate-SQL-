package com.BoostingWebsite.order;

import com.BoostingWebsite.utils.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/order/orderHistory")
class OrderHistoryController extends BaseController {
    private final OrderHistoryFacade facade;

    OrderHistoryController(OrderHistoryFacade facade) {
        this.facade = facade;
    }

    @GetMapping
    String orderPreviewPage(Model model) {
        model.addAttribute("orderHistory", facade.getCompletedOrderBoosts());
        return "order/orderHistory";
    }
}
