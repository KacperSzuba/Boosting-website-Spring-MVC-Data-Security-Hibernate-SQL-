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
@RequestMapping("/account/change/password")
class PasswordManagerController extends BaseController {
    private static final Logger logger  = LoggerFactory.getLogger(PasswordManagerController.class);

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
        } catch (DataMismatchException ex) {
            logger.error("Error during changing password!", ex);
            model.addAttribute("newPassword", ex.getMessage());
        }

        return "account/change-password";
    }

}
