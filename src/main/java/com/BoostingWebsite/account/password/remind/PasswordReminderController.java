package com.BoostingWebsite.account.password.remind;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/account")
class PasswordReminderController {

    private final PasswordReminder passwordReminder;
    private final PasswordReminderToken passwordReminderToken;

    PasswordReminderController(PasswordReminder passwordReminder, PasswordReminderToken passwordReminderToken) {
        this.passwordReminder = passwordReminder;
        this.passwordReminderToken = passwordReminderToken;
    }

    @GetMapping("/remind/password")
    String remindPasswordPage() {
        return "account/remind-password";
    }

    @GetMapping("/remind/password/form")
    String remindPassword(@RequestParam("email") String email) {
        passwordReminder.remindPassword(email);
        return "account/remind-password";
    }

    @GetMapping("/remind/password/token")
    String remind(@RequestParam("id") Long id, @RequestParam("token") String token, Model model) {
        String validatePasswordResetToken = passwordReminderToken.validateResetPasswordToken(id, token);
        if (validatePasswordResetToken != null) {
            model.addAttribute("token", "Your token is " + validatePasswordResetToken);
            return "accountView/login";
        }
        return "redirect:/account/reset/password";
    }

    @GetMapping("/reset/password")
    String updatePassword() {
        return "account/update-password";
    }

    @PostMapping("/reset/password")
    String resetPassword(@RequestParam("password") String password, @RequestParam("repeatPassword") String repeatPassword, Model model) {
        passwordReminder.resetPassword(password, repeatPassword);
        if (passwordReminder.getPasswordRemindMessage() != null) {
            model.addAttribute("message", passwordReminder.getPasswordRemindMessage());
            return "account/update-password";
        }
        return "redirect:/login";
    }
}
