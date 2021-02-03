package com.BoostingWebsite.account;

import com.BoostingWebsite.account.exception.DataMismatchException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/account/change/password")
class PasswordManagerController {

    private final PasswordManager passwordManager;

    PasswordManagerController(PasswordManager passwordManager) {
        this.passwordManager = passwordManager;
    }

    @GetMapping
    String showChangePasswordPage() {
        return "account/change-password";
    }

    @GetMapping("/form")
    String changePassword(@RequestParam("currentPassword") String currentPassword, @RequestParam("password") String password, @RequestParam("repeatPassword") String repeatPassword, Model model) {
        try {
            passwordManager.changePassword(currentPassword, password, repeatPassword);
            model.addAttribute("newPassword", "Your password has been successfully changed to: " + password);
        } catch (DataMismatchException e) {
            model.addAttribute("newPassword", e.getMessage());
        }

        return "account/change-password";
    }

}
