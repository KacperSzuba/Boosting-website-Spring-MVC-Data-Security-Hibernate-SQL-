package com.BoostingWebsite.order;

import com.BoostingWebsite.api.LeagueOfLegendsAPIFacade;
import com.BoostingWebsite.order.exception.OrderBoostNotFoundException;
import com.BoostingWebsite.order.message.MessageFacade;
import com.BoostingWebsite.order.message.dto.MessageDTO;
import com.BoostingWebsite.utils.ApplicationSession;
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

    private final ApplicationSession applicationSession;
    private final OrderBoostFacade orderBoostFacade;
    private final MessageFacade messageFacade;
    private final LeagueOfLegendsAPIFacade leagueOfLegendsAPIFacade;

    OrderPreviewController(ApplicationSession applicationSession, OrderBoostFacade orderBoostFacade, MessageFacade messageFacade, LeagueOfLegendsAPIFacade leagueOfLegendsAPIFacade) {
        this.orderBoostFacade = orderBoostFacade;
        this.messageFacade = messageFacade;
        this.leagueOfLegendsAPIFacade = leagueOfLegendsAPIFacade;
        this.applicationSession = applicationSession;
    }

    @GetMapping
    String orderPreviewPage(Model model) {
        try{
            OrderBoost orderBoost = orderBoostFacade.findActiveBoost();

            model.addAttribute("orderBoost", orderBoost);
            model.addAttribute("messages", messageFacade.getChatMessages(orderBoost.getUser(), orderBoost.getBooster()));
            model.addAttribute("username", applicationSession.getActualUser().getUsername());
            model.addAttribute("currentLeague", leagueOfLegendsAPIFacade.getCurrentLeague());
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
        messageFacade.saveMessage(messageDTO);
        return messageDTO;
    }

    @MessageMapping("/chat.newUser")
    @SendTo("/topic/javainuse")
    MessageDTO addUser(@Payload MessageDTO messageDTO, SimpMessageHeaderAccessor headerAccessor) {
        //headerAccessor.getSessionAttributes().put("senderName", messageDTO.getSenderName());
        return messageDTO;
    }
}
