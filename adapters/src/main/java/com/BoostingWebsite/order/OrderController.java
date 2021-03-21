package com.BoostingWebsite.order;

import com.BoostingWebsite.boosterApplication.Region;
import com.BoostingWebsite.utils.BaseController;
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
class OrderController extends BaseController {
    private final OrderFacade facade;

    OrderController(OrderFacade facade) {
        this.facade = facade;
    }

    @GetMapping
    String showOrderPage(Model model) {
        model.addAttribute("tiers", Tier.values());
        model.addAttribute("divisions", Division.values());
        model.addAttribute("points", Points.values());
        model.addAttribute("regions", Region.values());
        model.addAttribute("LPGainPerWin", LPGainPerWin.values());
        model.addAttribute("queueType", QueueType.values());
        model.addAttribute("defaultSelectedQueueType", QueueType.RANKED_SOLO_DUO.getName());
        model.addAttribute("extras", facade.getOrderExtras());

        try {
            model.addAttribute("whetherUserHasOrder", facade.whetherUserHasOrder());
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
        else {
            try {
                facade.makeOrder(orderBoost);
                return "redirect:/";
            } catch (IllegalArgumentException ex) {
                ex.printStackTrace();
                return "order/order";
            }
        }
    }
}
