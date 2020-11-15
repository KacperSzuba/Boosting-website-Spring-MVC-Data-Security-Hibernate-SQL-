package com.BoostingWebsite.account.email;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/account")
class EmailManagerController {

    private final EmailManager emailManager;

    EmailManagerController(EmailManager emailManager) {
        this.emailManager = emailManager;
    }

    @GetMapping("/change/email")
    public String emailChangePage() {
        return "account/change-email";
    }

    @GetMapping("/change/email/form")
    public String changeEmail(@RequestParam("currentEmail") String currentEmail, @RequestParam("email") String email,
                              @RequestParam("confirmEmail") String confirmEmail, Model model) {
        emailManager.changeEmail(currentEmail, email, confirmEmail);
        model.addAttribute("newEmail", emailManager.getMessage());
        return "account/change-email";
    }

}
