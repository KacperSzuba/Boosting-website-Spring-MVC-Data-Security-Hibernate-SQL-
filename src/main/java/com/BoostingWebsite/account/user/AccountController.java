package com.BoostingWebsite.account.user;

import com.BoostingWebsite.account.email.EmailManager;
import com.BoostingWebsite.account.password.forgot.PasswordReminder;
import com.BoostingWebsite.account.password.reset.PasswordManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/account")
public class AccountController {

    private final PasswordManager passwordManager;
    private final EmailManager emailChange;
    private final PasswordReminder passwordReminder;
    public AccountController(PasswordManager passwordManager, EmailManager emailChange, PasswordReminder passwordReminder) {
        this.passwordManager = passwordManager;
        this.emailChange = emailChange;
        this.passwordReminder = passwordReminder;
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
    public String remindPassword(@RequestParam("email") String email){
        passwordReminder.remindPassword(email);
        return "accountView/user_RemindPassword";
    }

    @GetMapping("/remindPassword")
    public String remind(@RequestParam("id")Long id, @RequestParam("token") String token,Model model){
        String validatePasswordResetToken = passwordReminder.validatePasswordResetToken(id,token);
        if(validatePasswordResetToken != null){
            model.addAttribute("token","Your token is "+validatePasswordResetToken);
            return "accountView/login";
        }
        return "redirect:/account/updatePassword";
    }

    @GetMapping("/updatePassword")
    public String updatePassword(){
        return "accountView/updatePassword";
    }

    @PostMapping("/resetPassword")
    public String resetPassword(@RequestParam("password") String password,@RequestParam("repeatPassword") String repeatPassword) {
        passwordReminder.resetPassword(password,repeatPassword);
        return "redirect:/login";
    }

}