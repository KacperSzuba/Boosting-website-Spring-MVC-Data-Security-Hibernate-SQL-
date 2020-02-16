package pl.javastart.contoller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.javastart.model.entity.order.OrderBoost;
import pl.javastart.model.enums.Region;
import pl.javastart.service.OrderService;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/order")

public class OrderController {

    @Autowired
    private OrderService orderService;

    @RequestMapping("/")
    public String showOrderPage(){
        return "orderView/order_division";
    }

    @RequestMapping("/informationAboutDivision")
    public String informationAboutDivision(Model model){
        OrderBoost orderBoost = new OrderBoost();
        model.addAttribute("orderBoost",orderBoost);
        model.addAttribute("listOfRegions", Region.values());
        return "orderView/order";
    }

    @RequestMapping("/informationAboutAccount")
    public String informationAboutAccount(@ModelAttribute("orderBoost") OrderBoost orderBoost, HttpServletRequest request){
        orderService.makeOrder(orderBoost,request);
        return "orderView/order_result";
    }
}