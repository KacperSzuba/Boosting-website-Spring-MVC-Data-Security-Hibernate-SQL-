package pl.javastart.contoller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.javastart.service.OrderService;

@RequestMapping("/order")
@Controller
public class OrderController {

    @Autowired
    private OrderService orderService;

    @RequestMapping("/showOrderPage")
    public String showOrderPage(){
        return "jsp/order";
    }

    @RequestMapping("/makeOrder")
    public String makeOrder(){
        orderService.makeOrder();
        return "jsp/order";
    }
}
