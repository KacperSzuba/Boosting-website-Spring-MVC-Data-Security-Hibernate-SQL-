package com.BoostingWebsite.order.preview;

import com.BoostingWebsite.account.user.ApplicationSession;
import com.BoostingWebsite.order.entity.OrderBoost;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/order/preview")
public class OrderPreviewController {

    private final OrderPreviewService orderPreviewService;
    private final ApplicationSession applicationSession;

    public OrderPreviewController(OrderPreviewService orderPreviewService, ApplicationSession applicationSession) {
        this.orderPreviewService = orderPreviewService;
        this.applicationSession = applicationSession;
    }

    @GetMapping
    public String orderPreviewPage(Model model) {
        try{
            model.addAttribute("orderBoost", orderPreviewService.getOrderBoost());
            model.addAttribute("messages", orderPreviewService.getExistingChatMessages());
            model.addAttribute("username", applicationSession.getActualUser().getUsername());
        }
        catch (OrderBoostNotFoundException ex){
            model.addAttribute("orderBoost", new OrderBoost());
            ex.getStackTrace();
        }

        return "order/orderPreview";
    }

    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/javainuse")
    public MessageDTO sendMessage(@Payload MessageDTO messageDTO) {
        orderPreviewService.saveMessage(messageDTO);
        return messageDTO;
    }

    @MessageMapping("/chat.newUser")
    @SendTo("/topic/javainuse")
    public MessageDTO addUser(@Payload MessageDTO messageDTO, SimpMessageHeaderAccessor headerAccessor) {
        //headerAccessor.getSessionAttributes().put("senderName", messageDTO.getSenderName());
        return messageDTO;
    }
}
