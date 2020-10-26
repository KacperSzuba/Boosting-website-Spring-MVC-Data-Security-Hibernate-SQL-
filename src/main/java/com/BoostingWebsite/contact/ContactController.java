package com.BoostingWebsite.contact;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

import java.time.LocalDate;

import static com.BoostingWebsite.validator.EmailValidator.validateEmail;

@Controller
@RequestMapping("/contact-us")
public class ContactController {

    private final ContactRepository contactRepository;

    public ContactController(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    @GetMapping
    public String showContactUsPage(Model model) {
        model.addAttribute("contact", new Contact());
        return "contact-us";
    }

    @GetMapping("/send")
    public String send(@Valid @ModelAttribute("contact") Contact contact, BindingResult result) {
        if (result.hasErrors()) {
            return "contact-us";
        } else {
            if (validateEmail(contact.getEmail())) {
                contact.setDate(LocalDate.now());
                contactRepository.save(contact);
                return "redirect:/";
            } else {
                return "redirect:/contact-us";
            }
        }
    }
}
