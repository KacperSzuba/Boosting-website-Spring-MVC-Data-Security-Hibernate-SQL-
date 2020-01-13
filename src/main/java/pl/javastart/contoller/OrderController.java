package pl.javastart.contoller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.javastart.manage.animation.OrderBoostAnimationHandler;
import pl.javastart.model.entity.Division;
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
        Division defaultCurrentTier = orderBoostAnimationHandler.getDefaultCurrentTier();
        session.setAttribute("currentTierClass",defaultCurrentTier);
        model.addAttribute("currentTierDivision",defaultCurrentTier.getTier()+" "+defaultCurrentTier.getDivision());
        model.addAttribute("currentTierImage",defaultCurrentTier.getImgSource());
        Division defaultDestinationTier = orderBoostAnimationHandler.getDefaultDestinationTier();
        session.setAttribute("destinationTierClass",defaultDestinationTier);
        model.addAttribute("destinationTierDivision",defaultDestinationTier.getTier()+" "+defaultDestinationTier.getDivision());
        model.addAttribute("destinationTierImage",defaultDestinationTier.getImgSource());
        return "jsp/orderView/order_CompleteTheInformationAboutTheDivision";
    }

    @RequestMapping("/informationAboutDivision")
    public String informationAboutDivision(Model model){
        OrderBoost orderBoost = new OrderBoost();
        model.addAttribute("orderBoost",orderBoost);
        model.addAttribute("listOfRegions", Region.values());
        return "jsp/orderView/order_CompleteYourAccountInformation";
    }

    @RequestMapping("/informationAboutAccount")
    public String informationAboutAccount(@ModelAttribute("orderBoost") OrderBoost orderBoost,HttpServletRequest request){
        orderService.makeOrder(orderBoost,request);
        return "jsp/orderView/order_ConfirmOrder";
    }

    @RequestMapping("/moveCurrentTierImageToLeft")
    public String moveCurrentTierImageToLeft(HttpSession session, HttpServletRequest request, Model model){
        Division division = (Division) request.getSession().getAttribute("currentTierClass");
        Division division2 = orderBoostAnimationHandler.moveImageToLeft(division);
        session.removeAttribute("currentTierClass");
        session.setAttribute("currentTierClass", division2);
        model.addAttribute("currentTierDivision", division2.getTier()+" "+ division2.getDivision()+" ID:"+ division2.getId());
        model.addAttribute("currentTierImage", division2.getImgSource());
        Division defaultDestinationTier = (Division) request.getSession().getAttribute("destinationTierClass");
        model.addAttribute("destinationTierDivision",defaultDestinationTier.getTier()+" "+defaultDestinationTier.getDivision());
        model.addAttribute("destinationTierImage",defaultDestinationTier.getImgSource());
        return "jsp/orderView/order_CompleteTheInformationAboutTheDivision";
    }

    @RequestMapping("/moveCurrentTierImageToRight")
    public String moveCurrentTierImageToRight(HttpSession session, HttpServletRequest request, Model model){
        Division division = (Division) request.getSession().getAttribute("currentTierClass");
        Division division2 = orderBoostAnimationHandler.moveImageToRight(division);
        session.removeAttribute("currentTierClass");
        session.setAttribute("currentTierClass", division2);
        model.addAttribute("currentTierDivision", division2.getTier()+" "+ division2.getDivision()+" ID:"+ division2.getId());
        model.addAttribute("currentTierImage", division2.getImgSource());
        Division defaultDestinationTier = (Division) request.getSession().getAttribute("destinationTierClass");
        model.addAttribute("destinationTierDivision",defaultDestinationTier.getTier()+" "+defaultDestinationTier.getDivision());
        model.addAttribute("destinationTierImage",defaultDestinationTier.getImgSource());
        return "jsp/orderView/order_CompleteTheInformationAboutTheDivision";
    }

    @RequestMapping("/moveCurrentTierImageUp")
    public String moveCurrentTierImageUp(HttpSession session, HttpServletRequest request, Model model){
        Division division = (Division) request.getSession().getAttribute("currentTierClass");
        Division division2 = orderBoostAnimationHandler.moveImageUp(division);
        session.removeAttribute("currentTierClass");
        session.setAttribute("currentTierClass", division2);
        model.addAttribute("currentTierDivision", division2.getTier()+" "+ division2.getDivision()+" ID:"+ division2.getId());
        model.addAttribute("currentTierImage", division2.getImgSource());
        Division defaultDestinationTier = (Division) request.getSession().getAttribute("destinationTierClass");
        model.addAttribute("destinationTierDivision",defaultDestinationTier.getTier()+" "+defaultDestinationTier.getDivision());
        model.addAttribute("destinationTierImage",defaultDestinationTier.getImgSource());
        return "jsp/orderView/order_CompleteTheInformationAboutTheDivision";
    }

    @RequestMapping("/moveCurrentTierImageDown")
    public String moveCurrentTierImageDown(HttpSession session, HttpServletRequest request, Model model){
        Division division = (Division) request.getSession().getAttribute("currentTierClass");
        Division division2 = orderBoostAnimationHandler.moveImageDown(division);
        session.removeAttribute("currentTierClass");
        session.setAttribute("currentTierClass", division2);
        model.addAttribute("currentTierDivision", division2.getTier()+" "+ division2.getDivision()+" ID:"+ division2.getId());
        model.addAttribute("currentTierImage", division2.getImgSource());
        Division defaultDestinationTier = (Division) request.getSession().getAttribute("destinationTierClass");
        model.addAttribute("destinationTierDivision",defaultDestinationTier.getTier()+" "+defaultDestinationTier.getDivision());
        model.addAttribute("destinationTierImage",defaultDestinationTier.getImgSource());
        return "jsp/orderView/order_CompleteTheInformationAboutTheDivision";
    }

    @RequestMapping("/moveDestinationTierImageLeft")
    public String moveDestinationTierImageLeft(HttpSession session, HttpServletRequest request, Model model){
        Division division = (Division) request.getSession().getAttribute("destinationTierClass");
        Division division2 = orderBoostAnimationHandler.moveImageToLeft(division);
        session.removeAttribute("destinationTierClass");
        session.setAttribute("destinationTierClass", division2);
        model.addAttribute("destinationTierDivision", division2.getTier()+" "+ division2.getDivision()+" ID:"+ division2.getId());
        model.addAttribute("destinationTierImage", division2.getImgSource());
        Division currentTier = (Division) request.getSession().getAttribute("currentTierClass");
        model.addAttribute("currentTierDivision",currentTier.getTier()+" "+currentTier.getDivision()+" ID:"+currentTier.getId());
        model.addAttribute("currentTierImage",currentTier.getImgSource());
        return "jsp/orderView/order_CompleteTheInformationAboutTheDivision";
    }

    @RequestMapping("/moveDestinationTierImageRight")
    public String moveDestinationTierImageRight(HttpSession session, HttpServletRequest request, Model model){
        Division division = (Division) request.getSession().getAttribute("destinationTierClass");
        Division division2 = orderBoostAnimationHandler.moveImageToRight(division);
        session.removeAttribute("destinationTierClass");
        session.setAttribute("destinationTierClass", division2);
        model.addAttribute("destinationTierDivision", division2.getTier()+" "+ division2.getDivision()+" ID:"+ division2.getId());
        model.addAttribute("destinationTierImage", division2.getImgSource());
        Division currentTier = (Division) request.getSession().getAttribute("currentTierClass");
        model.addAttribute("currentTierDivision",currentTier.getTier()+" "+currentTier.getDivision()+" ID:"+currentTier.getId());
        model.addAttribute("currentTierImage",currentTier.getImgSource());
        return "jsp/orderView/order_CompleteTheInformationAboutTheDivision";
    }

    @RequestMapping("/moveDestinationTierImageUp")
    public String moveDestinationTierImageUp(HttpSession session, HttpServletRequest request, Model model){
        Division division = (Division) request.getSession().getAttribute("destinationTierClass");
        Division division2 = orderBoostAnimationHandler.moveImageUp(division);
        session.removeAttribute("destinationTierClass");
        session.setAttribute("destinationTierClass", division2);
        model.addAttribute("destinationTierDivision", division2.getTier()+" "+ division2.getDivision()+" ID:"+ division2.getId());
        model.addAttribute("destinationTierImage", division2.getImgSource());
        Division currentTier = (Division) request.getSession().getAttribute("currentTierClass");
        model.addAttribute("currentTierDivision",currentTier.getTier()+" "+currentTier.getDivision()+" ID:"+currentTier.getId());
        model.addAttribute("currentTierImage",currentTier.getImgSource());
        return "jsp/orderView/order_CompleteTheInformationAboutTheDivision";
    }

    @RequestMapping("/moveDestinationTierImageDown")
    public String moveDestinationTierImageDown(HttpSession session, HttpServletRequest request, Model model){
        Division division = (Division) request.getSession().getAttribute("destinationTierClass");
        Division division2 = orderBoostAnimationHandler.moveImageDown(division);
        session.removeAttribute("destinationTierClass");
        session.setAttribute("destinationTierClass", division2);
        model.addAttribute("destinationTierDivision", division2.getTier()+" "+ division2.getDivision()+" ID:"+ division2.getId());
        model.addAttribute("destinationTierImage", division2.getImgSource());
        Division currentTier = (Division) request.getSession().getAttribute("currentTierClass");
        model.addAttribute("currentTierDivision",currentTier.getTier()+" "+currentTier.getDivision()+" ID:"+currentTier.getId());
        model.addAttribute("currentTierImage",currentTier.getImgSource());
        return "jsp/orderView/order_CompleteTheInformationAboutTheDivision";
    }
}