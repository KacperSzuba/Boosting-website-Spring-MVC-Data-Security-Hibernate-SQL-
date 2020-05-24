package com.BoostingWebsite.order;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import com.BoostingWebsite.order.division.Region;

@Controller
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/")
    public String showOrderPage(){
        return "orderView/order_division";
    }

    @GetMapping("/informationAboutDivision")
    public String informationAboutDivision(Model model){
        OrderBoost orderBoost = new OrderBoost();
        model.addAttribute("orderBoost", orderBoost);
        model.addAttribute("listOfRegions", Region.values());
        return "orderView/order";
    }

    @GetMapping("/informationAboutAccount")
    public String informationAboutAccount(@ModelAttribute("orderBoost") OrderBoost orderBoost){
        orderService.makeOrder(orderBoost);
        return "orderView/order_result";
    }
}