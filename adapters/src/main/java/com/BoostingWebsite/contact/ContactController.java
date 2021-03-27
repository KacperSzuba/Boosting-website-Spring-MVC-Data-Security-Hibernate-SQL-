package com.BoostingWebsite.contact;

import com.BoostingWebsite.account.exception.EmailNotFoundException;
import com.BoostingWebsite.contact.dto.ContactDto;
import com.BoostingWebsite.utils.BaseController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

import static com.BoostingWebsite.utils.EmailValidator.whetherEmailIsValid;

@Controller
@RequestMapping("/contact-us")
class ContactController extends BaseController {
    private static final Logger logger  = LoggerFactory.getLogger(ContactController.class);

    private final ContactFacade facade;

    ContactController(ContactFacade facade) {
        this.facade = facade;
    }

    @GetMapping
    String showContactUsPage(Model model) {
        model.addAttribute("contactDto", new ContactDto());
        return "contact-us";
    }

    @PostMapping
    String send(@Valid @ModelAttribute("contactDto") ContactDto contactDto, BindingResult result) {
        if (result.hasErrors()) {
            return "contact-us";
        } else {
            try {
                facade.save(contactDto);
                return "redirect:/";
            } catch (EmailNotFoundException ex) {
                logger.error("Error during contact registration!", ex);
                return "redirect:/contact-us";
            }
        }
    }
}
