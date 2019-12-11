package pl.javastart.contoller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pl.javastart.manage.ActualUser;
import pl.javastart.message.MessageHandler;
import pl.javastart.model.entity.Message;
import pl.javastart.repository.MessageRepository;
import pl.javastart.repository.UserRepository;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/message")
public class MessageController {
    @Autowired
    private MessageHandler messageHandler;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private ActualUser actualUser;

    @RequestMapping
    public String showMessageForm(Model model){
        Message message = new Message();
        model.addAttribute("admin",userRepository.findByUsername("Admin123x").getUsername());
        model.addAttribute("message",message);

        return "jsp/messageHandler/sendMessage";
    }

    @RequestMapping("/send")
    public String sendMessage(@RequestParam("username") String username ,@ModelAttribute("message") Message message , HttpServletRequest request){
        messageHandler.sendMessage(request,message  ,username);
        messageRepository.findAllByUser(userRepository.findByUsername("Admin123x"));
        return "redirect:/message";
    }

    @RequestMapping("/retrieve")
    public ModelAndView getMessage2(HttpServletRequest request){
        return new ModelAndView("jsp/messageHandler/messagesReceived","messages",messageHandler.setOfRecipientId(request));
    }

    @GetMapping("/singleConversation/{id}")
    public String singleConversation(@PathVariable("id") final Long id, Model model,HttpServletRequest request){
        model.addAttribute("msg",messageRepository.findAllByUser(userRepository.findById(id).get()));
        model.addAttribute("msg2",messageRepository.findAllByUserAndUser2(userRepository.findById(id).get(),actualUser.getActualUser(request)));
        return "jsp/messageHandler/singleConversation";
    }
}