package com.BoostingWebsite.account.user;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.BoostingWebsite.account.email.EmailManager;
import com.BoostingWebsite.account.password.reset.PasswordManager;

@Controller
@RequestMapping("/account")
public class AccountController {

    private final PasswordManager passwordManager;
    private final EmailManager emailChange;

    public AccountController(PasswordManager passwordManager, EmailManager emailChange) {
        this.passwordManager = passwordManager;
        this.emailChange = emailChange;
    }

    @GetMapping
    public String showAccountPage(){
        return "accountView/account";
    }

    @GetMapping("/showChangePasswordPage")
    public String showChangePasswordPage(){
        return "accountView/user_ChangePassword";
    }

    @GetMapping("/changePasswordForm")
    public String changePassword(@RequestParam("currentPassword")String currentPassword, @RequestParam ("password") String password,
        @RequestParam("repeatPassword")String repeatPassword, Model model){
        passwordManager.changePassword(currentPassword, password, repeatPassword);
        model.addAttribute("newPassword",passwordManager.getMessage());
        return "accountView/user_ChangePassword";
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

    @GetMapping("/remindPasswordPage")
    public String showRemindPasswordPage(){
        return "accountView/user_RemindPassword";
    }

    @GetMapping("/remindPasswordForm")
    private String remindPassword(@RequestParam("email")String email, Model model){
        System.out.println(email);
        return "accountView/user_RemindPassword";
    }

}