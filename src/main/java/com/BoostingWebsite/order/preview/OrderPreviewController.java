package com.BoostingWebsite.order.preview;

import com.BoostingWebsite.order.entity.OrderBoost;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/order/preview")
public class OrderPreviewController {

    private final OrderPreviewService orderPreviewService;

    public OrderPreviewController(OrderPreviewService orderPreviewService) {
        this.orderPreviewService = orderPreviewService;
    }

    @GetMapping
    public String orderPreviewPage(Model model) {
        try{
            model.addAttribute("orderBoost", orderPreviewService.getOrderBoost());
        }
        catch (OrderBoostNotFoundException ex){
            model.addAttribute("orderBoost", new OrderBoost());
            ex.getStackTrace();
        }

        return "order/orderPreview";
    }
}
