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

    @RequestMapping("/")
    public String showOrderPage(){
        return "jsp/orderView/order_division";
    }

    @Autowired
    private OrderService orderService;

    @RequestMapping("/informationAboutDivision")
    public String informationAboutDivision(Model model){
        OrderBoost orderBoost = new OrderBoost();
        model.addAttribute("orderBoost",orderBoost);
        model.addAttribute("listOfRegions", Region.values());
        return "jsp/orderView/order";
    }

    @RequestMapping("/informationAboutAccount")
    public String informationAboutAccount(@ModelAttribute("orderBoost") OrderBoost orderBoost, HttpServletRequest request){
        orderService.makeOrder(orderBoost,request);
        return "jsp/orderView/order_result";
    }
}
