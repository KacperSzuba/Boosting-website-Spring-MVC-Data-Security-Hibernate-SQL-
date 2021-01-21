package com.BoostingWebsite.order.preview;

import com.BoostingWebsite.account.user.ApplicationSession;
import com.BoostingWebsite.order.entity.OrderBoost;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

@Controller
@RequestMapping("/order/preview")
class OrderPreviewController {
    private static final Logger logger = LogManager.getLogger(OrderPreviewController.class);

    private final OrderPreviewService orderPreviewService;
    private final ApplicationSession applicationSession;

    OrderPreviewController(OrderPreviewService orderPreviewService, ApplicationSession applicationSession) {
        this.orderPreviewService = orderPreviewService;
        this.applicationSession = applicationSession;
    }

    @GetMapping
    String orderPreviewPage(Model model) {
        try{
            model.addAttribute("orderBoost", orderPreviewService.getOrderBoost());
            model.addAttribute("messages", orderPreviewService.getChatMessages());
            model.addAttribute("username", applicationSession.getActualUser().getUsername());
            model.addAttribute("currentLeague", orderPreviewService.getCurrentLeague());
        }
        catch (OrderBoostNotFoundException | IOException ex){
            model.addAttribute("orderBoost", new OrderBoost());
            logger.error(ex);
        }

        return "order/orderPreview";
    }

    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/javainuse")
    MessageDTO sendMessage(@Payload MessageDTO messageDTO) {
        orderPreviewService.saveMessage(messageDTO);
        return messageDTO;
    }

    @MessageMapping("/chat.newUser")
    @SendTo("/topic/javainuse")
    MessageDTO addUser(@Payload MessageDTO messageDTO, SimpMessageHeaderAccessor headerAccessor) {
        //headerAccessor.getSessionAttributes().put("senderName", messageDTO.getSenderName());
        return messageDTO;
    }
}
