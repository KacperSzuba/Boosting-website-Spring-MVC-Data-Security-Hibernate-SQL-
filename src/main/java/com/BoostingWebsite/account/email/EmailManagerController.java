package com.BoostingWebsite.account.email;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/account")
class EmailManagerController {

    private final EmailManager emailChange;

    EmailManagerController(EmailManager emailChange) {
        this.emailChange = emailChange;
    }

    @GetMapping("/change/email")
    public String emailChangePage(){
        return "accountView/user_ChangeEmail";
    }

    @GetMapping("/change/email/form")
    public String changeEmail(@RequestParam("currentEmail")String currentEmail, @RequestParam ("email") String email,
                              @RequestParam("confirmEmail")String confirmEmail, Model model){
        emailChange.changeEmail(currentEmail, email, confirmEmail);
        model.addAttribute("newEmail",emailChange.getMessage());
        return "accountView/user_ChangeEmail";
    }

}
