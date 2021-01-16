package com.BoostingWebsite.order;

import com.BoostingWebsite.order.entity.OrderBoost;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/order")
class OrderController {

    private final OrderBoostService orderBoostService;

    public OrderController(OrderBoostService orderBoostService) {
        this.orderBoostService = orderBoostService;
    }

    @GetMapping
    String showOrderPage(Model model) {
        model.addAttribute("tiers", Tier.values());
        model.addAttribute("divisions", Division.values());
        model.addAttribute("points", Points.values());
        model.addAttribute("regions", Region.values());
        model.addAttribute("LPGainPerWin", LPGainPerWin.values());
        model.addAttribute("queueType", QueueType.values());
        model.addAttribute("defaultSelectedQueueType", QueueType.RANKED_SOLO_DUO);

        try {
            model.addAttribute("whetherUserHasOrder", orderBoostService.whetherUserHasOrder());
        }
        catch (NullPointerException ex){
            return "redirect:/login";
        }

        model.addAttribute("orderBoost", new OrderBoost());

        return "order/order";
    }

    @PostMapping
    String accountInformation(@Valid @ModelAttribute("orderBoost") OrderBoost orderBoost, BindingResult result) {
        if (result.hasErrors()) {
            return "order/order";
        }
        try {
            if (orderBoostService.isLeagueIsValid(orderBoost)) {
                orderBoostService.makeOrder(orderBoost);
                return "redirect:/";
            } else {
                return "order/order";
            }
        } catch (NullPointerException ex) {
            ex.printStackTrace();
            return "redirect:/login";
        }
    }
}
