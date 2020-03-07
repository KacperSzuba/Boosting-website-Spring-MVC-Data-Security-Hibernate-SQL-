package com.BoostingWebsite.account.email;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/account")
public class EmailManagerController {

    private final EmailManager emailChange;

    public EmailManagerController(EmailManager emailChange) {
        this.emailChange = emailChange;
    }

    @GetMapping("/showEmailChangePage")
    public String showEmailChangePage(){
        return "accountView/user_ChangeEmail";
    }

    @GetMapping("/changeEmailForm")
    public String changeEmail(@RequestParam("currentEmail")String currentEmail, @RequestParam ("email") String email,
                              @RequestParam("repeatEmail")String repeatEmail, Model model){
        emailChange.changeEmail(currentEmail, email, repeatEmail);
        model.addAttribute("newEmail",emailChange.getMessage());
        return "accountView/user_ChangeEmail";
    }

}
