package com.BoostingWebsite.account;

import com.BoostingWebsite.account.exception.DataMismatchException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/account/remind/password")
class PasswordReminderController {

    private final PasswordManager passwordManager;

    PasswordReminderController(PasswordManager passwordManager) {
        this.passwordManager = passwordManager;
    }

    @GetMapping
    String remindPasswordPage() {
        return "account/remind-password";
    }

    @GetMapping("/form")
    String remindPassword(@RequestParam("email") String email, Model model) {
        try {
            passwordManager.remindPassword(email);
            model.addAttribute("remindEmailMessage", "The application has been sent correctly");
        } catch (DataMismatchException e) {
            model.addAttribute("remindEmailMessage", e.getMessage());
        }
        return "account/remind-password";
    }

    @GetMapping("/token")
    String remind(@RequestParam("id") Long id, @RequestParam("token") String token, Model model) {
        String validatePasswordResetToken = passwordManager.validateResetPasswordToken(id, token);
        if (validatePasswordResetToken != null) {
            model.addAttribute("token", "Your token is " + validatePasswordResetToken);
            return "accountView/login";
        }
        return "redirect:/account/reset/password";
    }
}
