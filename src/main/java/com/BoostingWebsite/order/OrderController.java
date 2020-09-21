package com.BoostingWebsite.order;

import com.BoostingWebsite.order.entity.OrderBoost;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/order")
public class OrderController {

    private OrderBoostService orderBoostService;

    public OrderController(OrderBoostService orderBoostService) {
        this.orderBoostService = orderBoostService;
    }

    @GetMapping
    public String showOrderPage(Model model){
        model.addAttribute("tiers", Tier.values());
        model.addAttribute("divisions", Division.values());
        model.addAttribute("points", Points.values());
        model.addAttribute("regions", Region.values());
        model.addAttribute("LPGainPerWin", LPGainPerWin.values());

        OrderBoost orderBoost = new OrderBoost();
        model.addAttribute("orderBoost", orderBoost);
        return "order/newOrder";
    }

    @GetMapping("/new")
    public String accountInformation(@Valid @ModelAttribute("orderBoost") OrderBoost orderBoost, BindingResult result){
        if(result.hasErrors()){
            return "order/newOrder";
        }
        try {
            if (orderBoostService.isLeagueIsValid(orderBoost)) {
                orderBoostService.makeOrder(orderBoost);
                return "redirect:/";
            } else {
                return "order/newOrder";
            }
        }
        catch (NullPointerException ex){
            ex.printStackTrace();
            return "redirect:/login";
        }
    }
}
