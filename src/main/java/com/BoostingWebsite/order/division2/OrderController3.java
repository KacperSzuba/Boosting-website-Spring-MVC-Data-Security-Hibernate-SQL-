package com.BoostingWebsite.order.division2;

import com.BoostingWebsite.order.division.Region;
import com.BoostingWebsite.order.division2.entity.OrderBoost2;
import com.BoostingWebsite.order.division2.repository.LeagueRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/order3")
public class OrderController3 {

    private LeagueRepository leagueRepository;
    private OrderBoostService2 orderBoostService2;

    public OrderController3(LeagueRepository leagueRepository, OrderBoostService2 orderBoostService2) {
        this.leagueRepository = leagueRepository;
        this.orderBoostService2 = orderBoostService2;
    }

    @GetMapping
    public String showOrderPage(Model model){
        model.addAttribute("tiers",leagueRepository.findAllTiersOrderByIdDesc());
        model.addAttribute("divisions",leagueRepository.findAllDivisions());
        model.addAttribute("points",leagueRepository.findAllPoints());
        model.addAttribute("regions", Region.values());

        OrderBoost2 orderBoost = new OrderBoost2();
        model.addAttribute("orderBoost2",orderBoost);
        return "orderView/newOrder";
    }

    @RequestMapping("/account/information")
    public String accountInformation(@Valid @ModelAttribute("orderBoost2") OrderBoost2 orderBoost2, BindingResult result){
        if(result.hasErrors()){
            return "orderView/newOrder";
        }
        try {
            if (orderBoostService2.isLeagueIsValid(orderBoost2)) {
                orderBoostService2.setLeagues(orderBoost2);
                orderBoostService2.setOrderInformation(orderBoost2);
                orderBoostService2.makeOrder();
                return "redirect:/";
            } else {
                return "orderView/newOrder";
            }
        }
        catch (NullPointerException ex){
            ex.printStackTrace();
            return "redirect:/login";
        }
    }
}
