package com.BoostingWebsite.account.mail;

import com.BoostingWebsite.account.exception.DataMismatchException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/account/change/email")
class EmailManagerController {

    private final EmailManager emailManager;

    EmailManagerController(EmailManager emailManager) {
        this.emailManager = emailManager;
    }

    @GetMapping
    String emailChangePage() {
        return "account/change-email";
    }

    @GetMapping("/form")
    String changeEmail(@RequestParam("currentEmail") String currentEmail, @RequestParam("email") String email, @RequestParam("confirmEmail") String confirmEmail, Model model) {
        try {
            emailManager.changeEmail(currentEmail, email, confirmEmail);
            model.addAttribute("newEmail", "Your e-mail has been successfully changed to: " + email);
        } catch (DataMismatchException e) {
            model.addAttribute("newEmail", e.getMessage());
        }
        return "account/change-email";
    }

}
