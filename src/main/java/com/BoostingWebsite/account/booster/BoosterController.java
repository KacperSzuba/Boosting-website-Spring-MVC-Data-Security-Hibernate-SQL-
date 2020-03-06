package com.BoostingWebsite.account.booster;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.BoostingWebsite.order.OrderBoost;
import com.BoostingWebsite.order.OrderBoostRepository;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/booster")
public class BoosterController {

    private final OrderBoostRepository orderBoostRepository;
    private final BoosterService boosterService;

    public BoosterController(OrderBoostRepository orderBoostRepository, BoosterService boosterService) {
        this.orderBoostRepository = orderBoostRepository;
        this.boosterService = boosterService;
    }

    @GetMapping
    public String showBoosterPage(){
        return "boosterView/booster";
    }

    @GetMapping("/listOfFreeOrders")
    public ModelAndView listOfFreeOrders(){
        List<OrderBoost> orders = boosterService.findFreeOrderBoost();
        return new ModelAndView("boosterView/listOfFreeOrders","orders",orders);
    }

    @GetMapping("/orderDetails/{id}")
    public String showOrderDetailsPage(@PathVariable("id")Long id, Model model){
        OrderBoost orderBoost = orderBoostRepository.findById(id).get();
        model.addAttribute("boostDetails",orderBoost);
        model.addAttribute("idOfBoost",id);
        return "boosterView/booster_OrderDetails";
    }

    @GetMapping("/orderDetails/{id}/addBoost")
    public String addBoost(@PathVariable("id")Long id, Model model){
        boosterService.addBoost(id);
        model.addAttribute("addingBoostMessage",boosterService.getMessage());
        return "boosterView/booster";
    }

    @GetMapping("/currentBoost")
    public ModelAndView currentBoost(){
        OrderBoost currentOrderBoost = boosterService.findCurrentBoost();
        return new ModelAndView("boosterView/currentBoost","currentOrderBoost",currentOrderBoost);
    }

    @GetMapping("/doneOrderBoosts")
    public ModelAndView listOfDoneOrderBoosts(){
        List<OrderBoost> listOfDoneOrderBoosts = boosterService.listOfDoneOrderBoosts();
        return new ModelAndView("boosterView/doneOrderBoosts","doneOrderBoosts",listOfDoneOrderBoosts);
    }

    @GetMapping("/currentBoost/finishBoost")
    public String finishBoost(Model model) throws IOException {
        boosterService.finishBoost();
        model.addAttribute("finishingBoostMessage",boosterService.getMessage());
        return "boosterView/booster";
    }
}
