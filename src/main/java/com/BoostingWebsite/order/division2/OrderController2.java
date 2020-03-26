package com.BoostingWebsite.order.division2;

import com.BoostingWebsite.order.division.Region;
import com.BoostingWebsite.order.division.Tier;
import com.BoostingWebsite.order.division2.repository.LeagueRepository;
import com.BoostingWebsite.order.division2.entity.OrderBoost2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@Controller
@RequestMapping("/order2")
public class OrderController2 {

    private LeagueRepository leagueRepository;
    private OrderBoostService2 orderBoostService2;
    private String incorrectLeagueChoice;

    public OrderController2(LeagueRepository leagueRepository, OrderBoostService2 orderBoostService2) {
        this.leagueRepository = leagueRepository;
        this.orderBoostService2 = orderBoostService2;
    }

    @GetMapping
    public String showOrderPage(Model model){
        model.addAttribute("tiers",leagueRepository.findAllTiersOrderByIdDesc());
        model.addAttribute("divisions",leagueRepository.findAllDivisions());
        model.addAttribute("points",leagueRepository.findAllPoints());
        OrderBoost2 orderBoost = new OrderBoost2();

        model.addAttribute("orderBoost2",orderBoost);
        model.addAttribute("incorrectLeagueChoice",incorrectLeagueChoice);
        return "orderView2/order2";
    }

    @GetMapping("/league/information")
    public String league(@ModelAttribute("orderBoost2")OrderBoost2 orderBoost2, Model model){
        if(orderBoostService2.isLeagueIsValid(orderBoost2)){
            orderBoostService2.setLeagues(orderBoost2);
            model.addAttribute("orderBoost2",orderBoost2);
            model.addAttribute("regions", Region.values());
            return "orderView2/order2_information_about_account";
        }
        incorrectLeagueChoice = "You choose incorrect League. Try it again.";
        return "redirect:/order2";
    }

    @RequestMapping("/account/information")
    public String accountInformation(@Valid @ModelAttribute("orderBoost2") OrderBoost2 orderBoost2, BindingResult result, Model model){
        if(result.hasErrors()){
            model.addAttribute("regions", Region.values());
            return "orderView2/order2_information_about_account";
        }
        orderBoostService2.setOrderInformation(orderBoost2);
        orderBoostService2.calculatePrice();
        orderBoostService2.makeOrder();
        return "redirect:/";
    }


}