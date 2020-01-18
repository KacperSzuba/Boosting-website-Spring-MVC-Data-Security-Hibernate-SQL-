package pl.javastart.contoller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.javastart.manage.json.JsonHandler;
import pl.javastart.model.entity.TierImage;
import pl.javastart.model.entity.order.OrderBoost;
import pl.javastart.model.enums.Region;
import pl.javastart.repository.DivisionsRepository;
import pl.javastart.repository.TierImageRepository;
import pl.javastart.repository.TierRepository;
import pl.javastart.service.NewOrderService;
import pl.javastart.service.OrderService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Controller
@RequestMapping("/NewOrder")

public class NewOrderController {

    @RequestMapping("/")
    public String showOrderPage(){
        return "jsp/orderView/order_division";
    }
    @Autowired
    private TierImageRepository tierImageRepository;
    /*
    @RequestMapping("/NewOrder/NewOrder")
    public String showOrderPage2(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("currentTierImageSource") || cookie.getName().equals("destinationTierImageSource")) {
                    System.out.println(tierImageRepository.findByImgSource(cookie.getValue()).getId());
                }
            }
        }
        return "jsp/orderView/order";
    }
*/
    @Autowired
    private NewOrderService newOrderService;

    @Autowired
    private TierRepository tierRepository;

    @Autowired
    private DivisionsRepository divisionsRepository;

    @RequestMapping("/informationAboutDivision")
    public String informationAboutDivision(Model model,HttpServletRequest request){
        TierImage currentLeague = newOrderService.cookies(request).get("currentTierImageSource");
        TierImage destinationLeague = newOrderService.cookies(request).get("destinationTierImageSource");
        System.out.println(tierRepository.findById(currentLeague.getTier().getId()).get().getTier());
        System.out.println(divisionsRepository.findById(currentLeague.getDivision().getId()).get().getDivision());
        System.out.println(tierRepository.findById(destinationLeague.getTier().getId()).get().getTier());

        OrderBoost orderBoost = new OrderBoost();
        model.addAttribute("orderBoost",orderBoost);
        model.addAttribute("listOfRegions", Region.values());
        return "jsp/orderView/order";
    }

    @RequestMapping("/informationAboutAccount")
    public String informationAboutAccount(@ModelAttribute("orderBoost") OrderBoost orderBoost, HttpServletRequest request){
        newOrderService.makeOrder(orderBoost,request);
        return "jsp/orderView/order_result";
    }
}
