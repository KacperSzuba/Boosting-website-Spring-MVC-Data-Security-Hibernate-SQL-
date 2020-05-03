package com.BoostingWebsite.contact;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import static com.BoostingWebsite.validator.EmailValidator.validateEmail;

@Controller
@RequestMapping("/contact-us")
public class ContactController {

    private final ContactRepository contactRepository;

    public ContactController(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    @GetMapping
    public String showContactUsPage(Model model){
        model.addAttribute("contact",new Contact());
        return "contact-us";
    }

    @GetMapping("/send")
    public String send(@ModelAttribute("contact") Contact contact){
        if (validateEmail(contact.getEmail())) {
            contactRepository.save(contact);
            return "redirect:/";
        }
        else {
            return "redirect:/contact-us";
        }
    }
}
