package com.BoostingWebsite.order.preview;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/order")
public class OrderPreviewController {

    @GetMapping("/{id}")
    public String orderPreviewPage(@PathVariable Long id) {
        return "order/orderPreview";
    }

}
