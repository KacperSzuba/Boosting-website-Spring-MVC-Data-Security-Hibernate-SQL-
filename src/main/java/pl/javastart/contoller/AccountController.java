package pl.javastart.contoller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.javastart.service.EmailManager;
import pl.javastart.service.PasswordManager;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private PasswordManager passwordManager;

    @Autowired
    private EmailManager emailChange;

    @RequestMapping
    public String showAccountPage(){
        return "jsp/account";
    }

    @GetMapping("/showChangePasswordPage")
    public String showChangePasswordPage(){
        return "jsp/user_ChangePassword";
    }

    @GetMapping("/changePasswordForm")
    public String changePassword(@RequestParam ("password") String password,
        @RequestParam("repeatPassword")String repeatPassword, Model model,HttpServletRequest request){
        passwordManager.changePassword(request,password,repeatPassword);
        model.addAttribute("newPassword", passwordManager.getMessage());
        return "jsp/user_ChangePassword";
    }

    @GetMapping("/showEmailChangePage")
    public String showEmailChangePage(){
        return "jsp/user_ChangeEmail";
    }

    @GetMapping("/changeEmailForm")
    public String changeEmail(@RequestParam ("email") String email,
        @RequestParam("repeatEmail")String repeatEmail, Model model,HttpServletRequest request){
        emailChange.changeEmail(request,email,repeatEmail);
        model.addAttribute("newEmail",emailChange.getMessage());
        return "jsp/user_ChangeEmail";
    }
}