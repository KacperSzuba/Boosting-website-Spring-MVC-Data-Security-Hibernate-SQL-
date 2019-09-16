package pl.javastart.contoller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import pl.javastart.model.entity.OrderBoost;
import pl.javastart.model.entity.User;
import pl.javastart.repository.OrderBoostRepository;

import java.util.List;

@Controller
@RequestMapping("/booster")
public class BoosterController {

    @Autowired
    private OrderBoostRepository orderBoostRepository;

    @RequestMapping
    public String showBoosterPage(){
        return "jsp/boosterPages/booster";
    }

    @RequestMapping("/listOfFreeOrders")
    public ModelAndView listOfFreeOrders(){
        List<OrderBoost> orders = (List<OrderBoost>) orderBoostRepository.findAll();
        return new ModelAndView("jsp/boosterPages/listOfFreeOrders","orders",orders);
    }
}
