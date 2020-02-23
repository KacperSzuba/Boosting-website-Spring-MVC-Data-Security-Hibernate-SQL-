package pl.javastart.contoller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import pl.javastart.model.entity.order.OrderBoost;
import pl.javastart.repository.order.OrderBoostRepository;
import pl.javastart.service.BoosterService;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/booster")
public class BoosterController {

    @Autowired
    private OrderBoostRepository orderBoostRepository;

    @Autowired
    private BoosterService boosterService;

    @RequestMapping
    public String showBoosterPage(){
        return "boosterView/booster";
    }

    @RequestMapping("/listOfFreeOrders")
    public ModelAndView listOfFreeOrders(){
        List<OrderBoost> orders = boosterService.findFreeOrderBoost();
        boosterService.findFreeOrderBoost().stream().map(OrderBoost::getBooster).forEach(System.out::println);
        return new ModelAndView("boosterView/listOfFreeOrders","orders",orders);
    }

    @RequestMapping("/orderDetails/{id}")
    public String showOrderDetailsPage(@PathVariable("id")Long id, Model model){
        OrderBoost orderBoost = orderBoostRepository.findById(id).get();
        model.addAttribute("boostDetails",orderBoost);
        model.addAttribute("idOfBoost",id);
        return "boosterView/booster_OrderDetails";
    }

    @RequestMapping("/orderDetails/{id}/addBoost")
    public String addBoost(@PathVariable("id")Long id,Model model){
        boosterService.addBoost(id);
        model.addAttribute("addingBoostMessage",boosterService.getMessage());
        return "boosterView/booster";
    }

    @RequestMapping("/currentBoost")
    public ModelAndView currentBoost(){
        OrderBoost currentOrderBoost = boosterService.findCurrentBoost();
        return new ModelAndView("boosterView/currentBoost","currentOrderBoost",currentOrderBoost);
    }

    @RequestMapping("/doneOrderBoosts")
    public ModelAndView listOfDoneOrderBoosts(){
        List<OrderBoost> listOfDoneOrderBoosts = boosterService.listOfDoneOrderBoosts();
        return new ModelAndView("boosterView/doneOrderBoosts","doneOrderBoosts",listOfDoneOrderBoosts);
    }

    @RequestMapping("/currentBoost/finishBoost")
    public String finishBoost(Model model) throws IOException {
        boosterService.finishBoost();
        model.addAttribute("finishingBoostMessage",boosterService.getMessage());
        return "boosterView/booster";
    }
}
