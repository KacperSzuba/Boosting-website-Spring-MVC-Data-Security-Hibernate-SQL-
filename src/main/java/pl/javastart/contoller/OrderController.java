package pl.javastart.contoller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/order")
@Controller
public class OrderController {

    @RequestMapping("/showOrderPage")
    public String showOrderPage(){
        return "jsp/order";
    }
}
