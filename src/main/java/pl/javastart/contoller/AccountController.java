package pl.javastart.contoller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.javastart.service.EmailManager;
import pl.javastart.service.PasswordManager;

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
    public String changePassword(@RequestParam("currentPassword")String currentPassword,@RequestParam ("password") String password,
        @RequestParam("repeatPassword")String repeatPassword, Model model){
        passwordManager.changePassword(currentPassword,password,repeatPassword);
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