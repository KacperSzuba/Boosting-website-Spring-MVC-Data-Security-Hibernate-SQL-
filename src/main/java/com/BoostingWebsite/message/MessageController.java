package com.BoostingWebsite.message;

import com.BoostingWebsite.account.user.ApplicationSession;
import com.BoostingWebsite.account.user.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/message")
public class MessageController {

    private final ApplicationSession applicationSession;
    private final MessageRepository messageRepository;
    private final UserRepository userRepository;

    public MessageController(ApplicationSession applicationSession, MessageRepository messageRepository, UserRepository userRepository) {
        this.applicationSession = applicationSession;
        this.messageRepository = messageRepository;
        this.userRepository = userRepository;
    }

    @GetMapping
    public String listOfRecipients(Model model){
        model.addAttribute("idOfActualUser", applicationSession.getActualUser().getId());
        model.addAttribute("recipients", messageRepository.listOfRecipients(applicationSession.getActualUser().getId()));
        return "messageView/messageRecipients";
    }

    @GetMapping("/{id}")
    public String newSingleConversation(@PathVariable("id") final Long id, Model model){
        Message message = new Message();
        model.addAttribute("sendMessage", message);
        model.addAttribute("id", id);
        messageRepository.list(id);
        return "messageView/singleConversation";
    }


    @PostMapping("/{id}")
    public String newSingleConversation2(@PathVariable("id") final Long id, @ModelAttribute("sendMessage") Message message){
        messageRepository.save(new Message(message.getMessage(), applicationSession.getActualUser(), userRepository.findById(id).get()));
        return "redirect:/message/{id}";
    }


}