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
    public String moveCurrentTierImageToLeft(HttpSession session, HttpServletRequest request, Model model){
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
    public String moveCurrentTierImageToRight(HttpSession session, HttpServletRequest request, Model model){
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
    public String moveCurrentTierImageUp(HttpSession session, HttpServletRequest request, Model model){
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
    public String moveCurrentTierImageDown(HttpSession session, HttpServletRequest request, Model model){
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

    @RequestMapping("/moveDestinationTierImageLeft")
    public String moveDestinationTierImageLeft(HttpSession session, HttpServletRequest request, Model model){
        Divisions divisions = (Divisions) request.getSession().getAttribute("destinationTierClass");
        Divisions divisions2 = orderBoostAnimationHandler.moveImageToLeft(divisions);
        session.removeAttribute("destinationTierClass");
        session.setAttribute("destinationTierClass",divisions2);
        model.addAttribute("destinationTierDivision",divisions2.getTier()+" "+divisions2.getDivision()+" ID:"+divisions2.getId());
        model.addAttribute("destinationTierImage",divisions2.getImgSource());
        Divisions currentTier = (Divisions) request.getSession().getAttribute("currentTierClass");
        model.addAttribute("currentTierDivision",currentTier.getTier()+" "+currentTier.getDivision()+" ID:"+currentTier.getId());
        model.addAttribute("currentTierImage",currentTier.getImgSource());
        return "jsp/order";
    }

    @RequestMapping("/moveDestinationTierImageRight")
    public String moveDestinationTierImageRight(HttpSession session, HttpServletRequest request, Model model){
        Divisions divisions = (Divisions) request.getSession().getAttribute("destinationTierClass");
        Divisions divisions2 = orderBoostAnimationHandler.moveImageToRight(divisions);
        session.removeAttribute("destinationTierClass");
        session.setAttribute("destinationTierClass",divisions2);
        model.addAttribute("destinationTierDivision",divisions2.getTier()+" "+divisions2.getDivision()+" ID:"+divisions2.getId());
        model.addAttribute("destinationTierImage",divisions2.getImgSource());
        Divisions currentTier = (Divisions) request.getSession().getAttribute("currentTierClass");
        model.addAttribute("currentTierDivision",currentTier.getTier()+" "+currentTier.getDivision()+" ID:"+currentTier.getId());
        model.addAttribute("currentTierImage",currentTier.getImgSource());
        return "jsp/order";
    }

    @RequestMapping("/moveDestinationTierImageUp")
    public String moveDestinationTierImageUp(HttpSession session, HttpServletRequest request, Model model){
        Divisions divisions = (Divisions) request.getSession().getAttribute("destinationTierClass");
        Divisions divisions2 = orderBoostAnimationHandler.moveImageUp(divisions);
        session.removeAttribute("destinationTierClass");
        session.setAttribute("destinationTierClass",divisions2);
        model.addAttribute("destinationTierDivision",divisions2.getTier()+" "+divisions2.getDivision()+" ID:"+divisions2.getId());
        model.addAttribute("destinationTierImage",divisions2.getImgSource());
        Divisions currentTier = (Divisions) request.getSession().getAttribute("currentTierClass");
        model.addAttribute("currentTierDivision",currentTier.getTier()+" "+currentTier.getDivision()+" ID:"+currentTier.getId());
        model.addAttribute("currentTierImage",currentTier.getImgSource());
        return "jsp/order";
    }

    @RequestMapping("/moveDestinationTierImageDown")
    public String moveDestinationTierImageDown(HttpSession session, HttpServletRequest request, Model model){
        Divisions divisions = (Divisions) request.getSession().getAttribute("destinationTierClass");
        Divisions divisions2 = orderBoostAnimationHandler.moveImageDown(divisions);
        session.removeAttribute("destinationTierClass");
        session.setAttribute("destinationTierClass",divisions2);
        model.addAttribute("destinationTierDivision",divisions2.getTier()+" "+divisions2.getDivision()+" ID:"+divisions2.getId());
        model.addAttribute("destinationTierImage",divisions2.getImgSource());
        Divisions currentTier = (Divisions) request.getSession().getAttribute("currentTierClass");
        model.addAttribute("currentTierDivision",currentTier.getTier()+" "+currentTier.getDivision()+" ID:"+currentTier.getId());
        model.addAttribute("currentTierImage",currentTier.getImgSource());
        return "jsp/order";
    }
}