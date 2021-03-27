package com.BoostingWebsite.account;

import com.BoostingWebsite.account.exception.DataMismatchException;
import com.BoostingWebsite.utils.BaseController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/account/remind/password")
class PasswordReminderController extends BaseController {
    private static final Logger logger  = LoggerFactory.getLogger(PasswordReminderController.class);

    private final PasswordReminderFacade facade;

    PasswordReminderController(PasswordReminderFacade facade) {
        this.facade = facade;
    }

    @GetMapping
    String remindPasswordPage() {
        return "account/remind-password";
    }

    @GetMapping("/form")
    String remindPassword(@RequestParam("email") String email, Model model) {
        try {
            facade.remindPassword(email);
            model.addAttribute("remindEmailMessage", "The application has been sent correctly");
        } catch (DataMismatchException ex) {
            logger.error("Error during reminding password!", ex);
            model.addAttribute("remindEmailMessage", ex.getMessage());
        }
        return "account/remind-password";
    }

    @GetMapping("/token")
    String remind(@RequestParam("id") Long id, @RequestParam("token") String token, Model model) {
        String validatePasswordResetToken = facade.confirm(id, token);
        if (validatePasswordResetToken != null) {
            model.addAttribute("token", "Your token is " + validatePasswordResetToken);
            return "accountView/login";
        }
        return "redirect:/account/reset/password";
    }
}
