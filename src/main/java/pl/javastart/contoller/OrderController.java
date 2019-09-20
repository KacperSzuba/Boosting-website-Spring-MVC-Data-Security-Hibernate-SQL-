package pl.javastart.contoller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.javastart.manage.animation.OrderBoostAnimationHandler;
import pl.javastart.model.entity.Divisions;
import pl.javastart.model.entity.OrderBoost;
import pl.javastart.model.entity.enums.Region;
import pl.javastart.service.OrderService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderBoostAnimationHandler orderBoostAnimationHandler;

    @RequestMapping
    public String showOrderPage(HttpSession session,Model model){
        Divisions defaultCurrentTier = orderBoostAnimationHandler.getDefaultCurrentTier();
        session.setAttribute("currentTierClass",defaultCurrentTier);
        model.addAttribute("currentTierDivision",defaultCurrentTier.getTier()+" "+defaultCurrentTier.getDivision());
        model.addAttribute("currentTierImage",defaultCurrentTier.getImgSource());
        Divisions defaultDestinationTier = orderBoostAnimationHandler.getDefaultDestinationTier();
        session.setAttribute("destinationTierClass",defaultDestinationTier);
        model.addAttribute("destinationTierDivision",defaultDestinationTier.getTier()+" "+defaultDestinationTier.getDivision());
        model.addAttribute("destinationTierImage",defaultDestinationTier.getImgSource());
        return "jsp/order_CompleteTheInformationAboutTheDivision";
    }

    @RequestMapping("/informationAboutDivision")
    public String informationAboutDivision(Model model){
        OrderBoost orderBoost = new OrderBoost();
        model.addAttribute("orderBoost",orderBoost);
        model.addAttribute("listOfRegions", Region.values());
        return "jsp/order_CompleteYourAccountInformation";
    }

    @RequestMapping("/informationAboutAccount")
    public String informationAboutAccount(@ModelAttribute("orderBoost") OrderBoost orderBoost,Model model,HttpServletRequest request){
        orderService.makeOrder(orderBoost,request);
        return "jsp/order_ConfirmOrder";
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
        return "jsp/order_CompleteTheInformationAboutTheDivision";
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
        return "jsp/order_CompleteTheInformationAboutTheDivision";
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
        return "jsp/order_CompleteTheInformationAboutTheDivision";
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
        return "jsp/order_CompleteTheInformationAboutTheDivision";
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
        return "jsp/order_CompleteTheInformationAboutTheDivision";
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
        return "jsp/order_CompleteTheInformationAboutTheDivision";
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
        return "jsp/order_CompleteTheInformationAboutTheDivision";
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
        return "jsp/order_CompleteTheInformationAboutTheDivision";
    }
}