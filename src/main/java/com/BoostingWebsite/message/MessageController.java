package com.BoostingWebsite.message;

import com.BoostingWebsite.account.user.ActualUser;
import com.BoostingWebsite.account.user.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/message")
public class MessageController {

    private final ActualUser actualUser;
    private final MessageRepository messageRepository;
    private final UserRepository userRepository;
    public MessageController(ActualUser actualUser, MessageRepository messageRepository, UserRepository userRepository) {
        this.actualUser = actualUser;
        this.messageRepository = messageRepository;
        this.userRepository = userRepository;
    }

    @GetMapping
    public String listOfRecipients(Model model){
        model.addAttribute("idOfActualUser",actualUser.getActualUser().getId());
        model.addAttribute("recipients",messageRepository.listOfRecipients());
        return "messageView/messageRecipients";
    }

    @GetMapping("/{id}")
    public String newSingleConversation(@PathVariable("id") final Long id, Model model){
        Message message = new Message();
        model.addAttribute("sendMessage",message);
        model.addAttribute("id",id);
        messageRepository.list(id);
        return "messageView/singleConversation";
    }

    @PostMapping("/{id}")
    public String newSingleConvrestaion2(@PathVariable("id") final Long id, @ModelAttribute("message") Message message){
        messageRepository.save(new Message(message.getMessage(), actualUser.getActualUser(), userRepository.findById(id).get()));
        return "redirect:/message/{id}";
    }

}