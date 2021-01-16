package com.BoostingWebsite.account.password.reset;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/account")
class PasswordManagerController {

    private final PasswordManager passwordManager;

    PasswordManagerController(PasswordManager passwordManager) {
        this.passwordManager = passwordManager;
    }

    @GetMapping("/change/password")
    String showChangePasswordPage() {
        return "account/change-password";
    }

    @GetMapping("/change/password/form")
    String changePassword(@RequestParam("currentPassword") String currentPassword, @RequestParam("password") String password,
                                 @RequestParam("repeatPassword") String repeatPassword, Model model) {
        passwordManager.changePassword(currentPassword, password, repeatPassword);
        model.addAttribute("newPassword", passwordManager.getMessage());
        return "account/change-password";
    }

}
