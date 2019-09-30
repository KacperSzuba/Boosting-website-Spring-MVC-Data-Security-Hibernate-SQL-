package pl.javastart.contoller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.javastart.service.EmailChange;
import pl.javastart.service.PasswordChange;
import pl.javastart.model.entity.User;
import pl.javastart.repository.UserRepository;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@Controller
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private PasswordChange passwordChange;

    @Autowired
    private EmailChange emailChange;

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
        passwordChange.changePassword(request,password,repeatPassword);
        model.addAttribute("newPassword",passwordChange.getMessage());
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