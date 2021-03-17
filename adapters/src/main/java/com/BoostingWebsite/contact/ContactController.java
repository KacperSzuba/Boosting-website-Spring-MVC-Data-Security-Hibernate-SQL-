package com.BoostingWebsite.contact;

import com.BoostingWebsite.utils.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

import static com.BoostingWebsite.utils.EmailValidator.whetherEmailIsValid;

@Controller
@RequestMapping("/contact-us")
class ContactController extends BaseController {

    private final ContactRepository contactRepository;

    ContactController(final ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    @GetMapping
    String showContactUsPage(Model model) {
        model.addAttribute("contact", new Contact());
        return "contact-us";
    }

    @GetMapping("/send")
    String send(@Valid @ModelAttribute("contact") Contact contact, BindingResult result) {
        if (result.hasErrors()) {
            return "contact-us";
        } else {
            if (whetherEmailIsValid(contact.getEmail())) {
                contactRepository.save(contact);
                return "redirect:/";
            } else {
                return "redirect:/contact-us";
            }
        }
    }
}
