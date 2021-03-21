package com.BoostingWebsite.order;

import com.BoostingWebsite.account.SimpleUserDto;
import com.BoostingWebsite.order.dto.OrderBoostDto;
import com.BoostingWebsite.order.exception.OrderBoostNotFoundException;
import com.BoostingWebsite.order.message.dto.MessageDTO;
import com.BoostingWebsite.utils.BaseController;
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
class OrderPreviewController extends BaseController {
    private static final Logger logger = LogManager.getLogger(OrderPreviewController.class);

    private final OrderPreviewFacade facade;

    OrderPreviewController(OrderPreviewFacade facade) {
        this.facade = facade;
    }

    @GetMapping
    String orderPreviewPage(Model model) {
        try{
            OrderBoostDto orderBoost = facade.findActiveBoost();

            model.addAttribute("orderBoost", orderBoost);
            model.addAttribute("messages", facade.getChatMessages(SimpleUserDto.restore(orderBoost.getUser()), SimpleUserDto.restore(orderBoost.getBooster())));
            model.addAttribute("username", applicationSession.getContext().getUser().getUsername());
            model.addAttribute("currentLeague", facade.getCurrentLeague());
        }
        catch (OrderBoostNotFoundException | IOException ex){
            model.addAttribute("orderBoost", OrderBoostDto.builder().build());
            logger.error(ex);
        }

        return "order/orderPreview";
    }

    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/javainuse")
    MessageDTO sendMessage(@Payload MessageDTO messageDTO) {
        facade.saveMessage(messageDTO);
        return messageDTO;
    }

    @MessageMapping("/chat.newUser")
    @SendTo("/topic/javainuse")
    MessageDTO addUser(@Payload MessageDTO messageDTO, SimpMessageHeaderAccessor headerAccessor) {
        //headerAccessor.getSessionAttributes().put("senderName", messageDTO.getSenderName());
        return messageDTO;
    }
}
