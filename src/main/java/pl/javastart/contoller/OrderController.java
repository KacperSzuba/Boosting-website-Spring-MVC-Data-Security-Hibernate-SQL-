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
        Divisions defaultCurrentTier = orderBoostAnimationHandler.getDefaultCurrentTier();
        session.setAttribute("currentTierClass",defaultCurrentTier);
        model.addAttribute("currentTierDivision",defaultCurrentTier.getTier()+" "+defaultCurrentTier.getDivision());
        model.addAttribute("currentTierImage",defaultCurrentTier.getImgSource());
        Divisions defaultDestinationTier = orderBoostAnimationHandler.getDefaultdestinationTier();
        session.setAttribute("destinationTierClass",defaultDestinationTier);
        model.addAttribute("destinationTierDivision",defaultDestinationTier.getTier()+" "+defaultDestinationTier.getDivision());
        model.addAttribute("destinationTierImage",defaultDestinationTier.getImgSource());
        return "jsp/order";
    }

    @RequestMapping("/makeOrder")
    public String makeOrder(){
        orderService.makeOrder();
        return "jsp/order";
    }

    @RequestMapping("/moveCurrentTierImageToLeft")
    public String moveImageToLeft(HttpSession session, HttpServletRequest request, Model model){
        Divisions divisions = (Divisions) request.getSession().getAttribute("currentTierClass");
        Divisions divisions2 = orderBoostAnimationHandler.moveImageToLeft(divisions);
        session.removeAttribute("currentTierClass");
        session.setAttribute("currentTierClass",divisions2);
        model.addAttribute("currentTierDivision",divisions2.getTier()+" "+divisions2.getDivision()+" ID:"+divisions2.getId());
        model.addAttribute("currentTierImage",divisions2.getImgSource());
        Divisions defaultDestinationTier = (Divisions) request.getSession().getAttribute("destinationTierClass");
        model.addAttribute("destinationTierDivision",defaultDestinationTier.getTier()+" "+defaultDestinationTier.getDivision());
        model.addAttribute("destinationTierImage",defaultDestinationTier.getImgSource());
        return "jsp/order";
    }

    @RequestMapping("/moveCurrentTierImageToRight")
    public String moveImageToRight(HttpSession session, HttpServletRequest request, Model model){
        Divisions divisions = (Divisions) request.getSession().getAttribute("currentTierClass");
        Divisions divisions2 = orderBoostAnimationHandler.moveImageToRight(divisions);
        session.removeAttribute("currentTierClass");
        session.setAttribute("currentTierClass",divisions2);
        model.addAttribute("currentTierDivision",divisions2.getTier()+" "+divisions2.getDivision()+" ID:"+divisions2.getId());
        model.addAttribute("currentTierImage",divisions2.getImgSource());
        Divisions defaultDestinationTier = (Divisions) request.getSession().getAttribute("destinationTierClass");
        model.addAttribute("destinationTierDivision",defaultDestinationTier.getTier()+" "+defaultDestinationTier.getDivision());
        model.addAttribute("destinationTierImage",defaultDestinationTier.getImgSource());
        return "jsp/order";
    }

    @RequestMapping("/moveCurrentTierImageUp")
    public String moveImageUp(HttpSession session, HttpServletRequest request, Model model){
        Divisions divisions = (Divisions) request.getSession().getAttribute("currentTierClass");
        Divisions divisions2 = orderBoostAnimationHandler.moveImageUp(divisions);
        session.removeAttribute("currentTierClass");
        session.setAttribute("currentTierClass",divisions2);
        model.addAttribute("currentTierDivision",divisions2.getTier()+" "+divisions2.getDivision()+" ID:"+divisions2.getId());
        model.addAttribute("currentTierImage",divisions2.getImgSource());
        Divisions defaultDestinationTier = (Divisions) request.getSession().getAttribute("destinationTierClass");
        model.addAttribute("destinationTierDivision",defaultDestinationTier.getTier()+" "+defaultDestinationTier.getDivision());
        model.addAttribute("destinationTierImage",defaultDestinationTier.getImgSource());
        return "jsp/order";
    }

    @RequestMapping("/moveCurrentTierImageDown")
    public String moveImageDown(HttpSession session, HttpServletRequest request, Model model){
        Divisions divisions = (Divisions) request.getSession().getAttribute("currentTierClass");
        Divisions divisions2 = orderBoostAnimationHandler.moveImageDown(divisions);
        session.removeAttribute("currentTierClass");
        session.setAttribute("currentTierClass",divisions2);
        model.addAttribute("currentTierDivision",divisions2.getTier()+" "+divisions2.getDivision()+" ID:"+divisions2.getId());
        model.addAttribute("currentTierImage",divisions2.getImgSource());
        Divisions defaultDestinationTier = (Divisions) request.getSession().getAttribute("destinationTierClass");
        model.addAttribute("destinationTierDivision",defaultDestinationTier.getTier()+" "+defaultDestinationTier.getDivision());
        model.addAttribute("destinationTierImage",defaultDestinationTier.getImgSource());
        return "jsp/order";
    }
}
