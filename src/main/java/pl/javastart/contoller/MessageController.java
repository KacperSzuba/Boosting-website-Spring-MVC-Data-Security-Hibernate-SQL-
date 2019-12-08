package pl.javastart.contoller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.javastart.message.MessageHandler;
import pl.javastart.model.entity.Message;
import pl.javastart.repository.UserRepository;


import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/message")
public class MessageController {
    @Autowired
    private MessageHandler messageHandler;

    @Autowired
    private UserRepository userRepository;

    @RequestMapping
    public String showMessageForm(Model model){
        Message message = new Message();
        model.addAttribute("admin",userRepository.findByUsername("Admin123x").getUsername());
        model.addAttribute("message",message);
        return "jsp/messageHandler/sendMessage";
    }

    @RequestMapping("/send")
    public String sendMessage(@RequestParam("username") String username ,@ModelAttribute("message") Message message , HttpServletRequest request){
        messageHandler.sendMessage(request,message,username);
        return "redirect:/message";
    }

    @RequestMapping("retrieve")
    public String getMessage(Model model,HttpServletRequest request){
        model.addAttribute("msg", messageHandler.listOfMessages(request));
        return "jsp/messageHandler/listOfMessages";
    }
}