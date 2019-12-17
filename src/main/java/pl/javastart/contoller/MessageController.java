package pl.javastart.contoller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pl.javastart.message.MessageHandler;
import pl.javastart.model.entity.Message;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/message")
public class MessageController {
    private final MessageHandler messageHandler;

    public MessageController(MessageHandler messageHandler) {
        this.messageHandler = messageHandler;
    }

    @RequestMapping
    public String messenger(Model model, HttpServletRequest request) {
        model.addAttribute("users",messageHandler.listOfRecipients(request));
        Message message = new Message();
        model.addAttribute("message",message);
        return "jsp/messageView/sendMessage";
    }

    @PostMapping("/send")
    public String sendMessage(@ModelAttribute("message") Message message , HttpServletRequest request){
        messageHandler.sendMessage(message,request);
        return "redirect:/message/singleConversation/"+messageHandler.getTemp();
    }

    @RequestMapping("/retrieve")
    public ModelAndView listOfConversations(HttpServletRequest request){
        return new ModelAndView("jsp/messageView/messagesReceived", "conversations",messageHandler.setOfSendMessages(request));
    }

    @GetMapping("/singleConversation/{id}")
    public String singleConversation(@PathVariable("id") final Long id) {
        messageHandler.setIdOfConversation(id);
        return "jsp/messageView/singleConversation";
    }
}