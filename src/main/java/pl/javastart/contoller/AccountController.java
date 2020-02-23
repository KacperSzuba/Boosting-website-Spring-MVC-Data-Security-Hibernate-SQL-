package pl.javastart.contoller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.javastart.service.EmailManager;
import pl.javastart.service.PasswordManager;

@Controller
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private PasswordManager passwordManager;

    @Autowired
    private EmailManager emailChange;

    @RequestMapping
    public String showAccountPage(){
        return "accountView/account";
    }

    @GetMapping("/showChangePasswordPage")
    public String showChangePasswordPage(){
        return "accountView/user_ChangePassword";
    }

    @GetMapping("/changePasswordForm")
    public String changePassword(@RequestParam ("password") String password,
        @RequestParam("repeatPassword")String repeatPassword, Model model){
        passwordManager.changePassword(password,repeatPassword);
        model.addAttribute("newPassword", passwordManager.getMessage());
        return "accountView/user_ChangePassword";
    }

    @GetMapping("/showEmailChangePage")
    public String showEmailChangePage(){
        return "accountView/user_ChangeEmail";
    }

    @GetMapping("/changeEmailForm")
    public String changeEmail(@RequestParam ("email") String email,
        @RequestParam("repeatEmail")String repeatEmail, Model model){
        emailChange.changeEmail(email,repeatEmail);
        model.addAttribute("newEmail",emailChange.getMessage());
        return "accountView/user_ChangeEmail";
    }
}