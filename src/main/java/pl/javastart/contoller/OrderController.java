package pl.javastart.contoller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.javastart.model.entity.Divisions;
import pl.javastart.manage.OrderBoostAnimationHandler;
import pl.javastart.model.entity.OrderBoost;
import pl.javastart.model.entity.User;
import pl.javastart.repository.UserRepository;
import pl.javastart.service.OrderService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.Principal;

@RequestMapping("/order")
@Controller
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserRepository userRepository;

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
        return "jsp/order_CompleteTheInformationAboutTheDivision";
    }

    @RequestMapping("/makeOrder")
    public String makeOrder(HttpSession session, HttpServletRequest request){
        Divisions currentDivision = (Divisions) request.getSession().getAttribute("currentTierClass");
        Divisions destinationDivision = (Divisions) request.getSession().getAttribute("destinationTierClass");
        Principal principal = request.getUserPrincipal();
        User user = userRepository.findByUsername(principal.getName());
        OrderBoost orderBoost = new OrderBoost();
        orderBoost.setCurrentTier(currentDivision.getTier());
        orderBoost.setCurrentDivision(currentDivision.getDivision());
        orderBoost.setDestinationTier(destinationDivision.getTier());
        orderBoost.setDestinationDivision(destinationDivision.getDivision());
        orderBoost.setUser(user);
        session.setAttribute("orderBoostClass",orderBoost);
        return "jsp/order_CompleteYourAccountInformation";
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