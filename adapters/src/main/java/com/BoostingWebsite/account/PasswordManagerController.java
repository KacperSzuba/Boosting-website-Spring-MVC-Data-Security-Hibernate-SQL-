package com.BoostingWebsite.account;

import com.BoostingWebsite.account.exception.DataMismatchException;
import com.BoostingWebsite.utils.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/account/change/password")
class PasswordManagerController extends BaseController {
    private final PasswordManagerFacade facade;

    PasswordManagerController(PasswordManagerFacade facade) {
        this.facade = facade;
    }

    @GetMapping
    String showChangePasswordPage() {
        return "account/change-password";
    }

    @GetMapping("/form")
    String changePassword(@RequestParam("currentPassword") String currentPassword, @RequestParam("password") String password, @RequestParam("repeatPassword") String repeatPassword, Model model) {
        try {
            facade.changePassword(currentPassword, password, repeatPassword);
            model.addAttribute("newPassword", "Your password has been successfully changed to: " + password);
        } catch (DataMismatchException e) {
            model.addAttribute("newPassword", e.getMessage());
        }

        return "account/change-password";
    }

}
