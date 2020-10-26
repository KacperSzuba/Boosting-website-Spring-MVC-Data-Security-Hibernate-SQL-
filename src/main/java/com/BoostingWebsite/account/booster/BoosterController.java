package com.BoostingWebsite.account.booster;

import com.BoostingWebsite.order.entity.OrderBoost;
import com.BoostingWebsite.order.repository.OrderBoostRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/boost")
class BoosterController {

    private final OrderBoostRepository orderBoostRepository;
    private final BoosterService boosterService;

    BoosterController(OrderBoostRepository orderBoostRepository, BoosterService boosterService) {
        this.orderBoostRepository = orderBoostRepository;
        this.boosterService = boosterService;
    }

    @GetMapping
    public String showBoosterPage() {
        return "boosterView/booster";
    }

    @GetMapping("/orders")
    public ModelAndView listOfFreeOrders() {
        List<OrderBoost> orders = boosterService.findFreeOrderBoost();
        return new ModelAndView("boosterView/listOfFreeOrders", "orders", orders);
    }

    @GetMapping("/order/{id}")
    public String showOrderDetailsPage(@PathVariable("id") Long id, Model model) {
        OrderBoost orderBoost = orderBoostRepository.findById(id).get();
        model.addAttribute("boostDetails", orderBoost);
        model.addAttribute("idOfBoost", id);
        return "boosterView/booster_OrderDetails";
    }

    @GetMapping("/order/{id}/add")
    public String addBoost(@PathVariable("id") Long id, Model model) {
        boosterService.addBoost(id);
        model.addAttribute("addingBoostMessage", boosterService.getMessage());
        return "boosterView/booster";
    }

    @GetMapping("/current")
    public ModelAndView currentBoost() {
        OrderBoost currentOrderBoost = boosterService.findCurrentBoost();
        return new ModelAndView("boosterView/currentBoost", "currentOrderBoost", currentOrderBoost);
    }

    @GetMapping("/completed")
    public ModelAndView listOfDoneOrderBoosts() {
        List<OrderBoost> listOfDoneOrderBoosts = boosterService.listOfDoneOrderBoosts();
        return new ModelAndView("boosterView/doneOrderBoosts", "doneOrderBoosts", listOfDoneOrderBoosts);
    }

    @GetMapping("/current/finish")
    public String finishBoost(Model model) throws IOException {
        boosterService.finishBoost();
        model.addAttribute("finishingBoostMessage", boosterService.getMessage());
        return "boosterView/booster";
    }
}
