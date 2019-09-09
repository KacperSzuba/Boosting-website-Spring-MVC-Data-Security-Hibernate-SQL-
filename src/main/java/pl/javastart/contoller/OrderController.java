package pl.javastart.contoller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.javastart.model.entity.Divisions;
import pl.javastart.manage.OrderBoostAnimationHandler;
import pl.javastart.service.OrderService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RequestMapping("/order")
@Controller
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderBoostAnimationHandler orderBoostAnimationHandler;

    @RequestMapping("/showOrderPage")
    public String showOrderPage(HttpSession session,Model model){

        Divisions divisions = orderBoostAnimationHandler.getDefaultCurrentTier();
        session.setAttribute("division",divisions);
        model.addAttribute("msg",divisions.getTier()+" "+divisions.getDivision());
        model.addAttribute("image",divisions.getImgSource());

        return "jsp/order";
    }

    @RequestMapping("/makeOrder")
    public String makeOrder(){
        orderService.makeOrder();
        return "jsp/order";
    }

    @RequestMapping("/moveCurrentTierImageToLeft")
    public String moveImageToLeft(HttpSession session, HttpServletRequest request, Model model){
        Divisions divisions = (Divisions) request.getSession().getAttribute("division");
        Divisions divisions2 = orderBoostAnimationHandler.moveImageToLeft(divisions);
        session.removeAttribute("division");
        session.setAttribute("division",divisions2);
        model.addAttribute("msg",divisions2.getTier()+" "+divisions2.getDivision()+" ID:"+divisions2.getId());
        model.addAttribute("image",divisions2.getImgSource());
        return "jsp/order";
    }

    @RequestMapping("/moveCurrentTierImageToRight")
    public String moveImageToRight(HttpSession session, HttpServletRequest request, Model model){
        Divisions divisions = (Divisions) request.getSession().getAttribute("division");
        Divisions divisions2 = orderBoostAnimationHandler.moveImageToRight(divisions);
        session.removeAttribute("division");
        session.setAttribute("division",divisions2);
        model.addAttribute("msg",divisions2.getTier()+" "+divisions2.getDivision()+" ID:"+divisions2.getId());
        model.addAttribute("image",divisions2.getImgSource());
        return "jsp/order";
    }

    @RequestMapping("/moveCurrentTierImageUp")
    public String moveImageUp(HttpSession session, HttpServletRequest request, Model model){
        Divisions divisions = (Divisions) request.getSession().getAttribute("division");
        Divisions divisions2 = orderBoostAnimationHandler.moveImageUp(divisions);
        session.removeAttribute("division");
        session.setAttribute("division",divisions2);
        model.addAttribute("msg",divisions2.getTier()+" "+divisions2.getDivision()+" ID:"+divisions2.getId());
        model.addAttribute("image",divisions2.getImgSource());
        return "jsp/order";
    }

    @RequestMapping("/moveCurrentTierImageDown")
    public String moveImageDown(HttpSession session, HttpServletRequest request, Model model){
        Divisions divisions = (Divisions) request.getSession().getAttribute("division");
        Divisions divisions2 = orderBoostAnimationHandler.moveImageDown(divisions);
        session.removeAttribute("division");
        session.setAttribute("division",divisions2);
        model.addAttribute("msg",divisions2.getTier()+" "+divisions2.getDivision());
        model.addAttribute("image",divisions2.getImgSource());
        return "jsp/order";
    }
}
