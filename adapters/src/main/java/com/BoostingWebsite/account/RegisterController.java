package com.BoostingWebsite.account;

import com.BoostingWebsite.account.exception.DataMismatchException;
import com.BoostingWebsite.utils.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Controller
@RequestMapping("/register")
class RegisterController extends BaseController {
    private final RegisterFacade facade;

    RegisterController(RegisterFacade facade) {
        this.facade = facade;
    }

    @GetMapping
    String registerPage(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "account/register";
    }

    @PostMapping
    String register(@Valid @ModelAttribute("user") User user, BindingResult result, @RequestParam("confirmPassword") String confirmPassword, Model model) {
        if (result.hasErrors()) {
            return "account/register";
        } else {
            try {
                facade.createAccount(user, confirmPassword);
                model.addAttribute("confirmEmailMessage", "Login to the e-mail address provided and confirm your identity.");
                return "account/login";
            } catch (DataMismatchException e) {
                model.addAttribute("confirmEmailMessage", e.getMessage());
                return "account/register";
            }
        }
    }

    @GetMapping("/confirm")
    String confirmEmail(@RequestParam("id") Long id, @RequestParam("token") String token) {
        String confirmEmail = facade.confirm(id, token);

        if (confirmEmail != null) {
            return "redirect:/login";
        }

        facade.enable(id);

        return "redirect:/login";
    }
}
