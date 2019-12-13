package pl.javastart.contoller;

import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    private MessageHandler messageHandler;

    @RequestMapping
    public String message(Model model,HttpServletRequest request){
        model.addAttribute("users",messageHandler.listOfRecipients(request));
        Message message = new Message();
        model.addAttribute("message",message);
        return "jsp/messageHandler/sendMessage";
    }

    @PostMapping("/send")
    public String sendMessage(@ModelAttribute("message") Message message , HttpServletRequest request){
        messageHandler.sendMessage(message,request);
        return "jsp/home";
    }

    @RequestMapping("/retrieve")
    public ModelAndView getMessage2(HttpServletRequest request){
        return new ModelAndView("jsp/messageHandler/messagesReceived","messages",messageHandler.setOfRecipientId(request));
    }

    @GetMapping("/singleConversation/{id}")
    public String singleConversation(@PathVariable("id") final Long id, Model model,HttpServletRequest request){
        model.addAttribute("msg2",messageHandler.getConversation(id,request));
        return "jsp/messageHandler/singleConversation";
    }
}