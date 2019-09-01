package pl.javastart.contoller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.javastart.manage.PasswordChange;
import pl.javastart.model.entity.User;
import pl.javastart.repository.UserRepository;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@Controller
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordChange passwordChange;

    @RequestMapping
    public String showAccountPage(){
        return "jsp/account";
    }

    @GetMapping("/showChangePasswordPage")
    public String showPage(){
        return "jsp/user_ChangePassword";
    }

    @GetMapping("/changePasswordForm")
    public String showChangePasswordPage(@RequestParam("password") String password,Model model,HttpServletRequest request){
        Principal principal = request.getUserPrincipal();
        User user = userRepository.findByUsername(principal.getName());
        passwordChange.changePassword(user.getId(),password);
        model.addAttribute("newPassword",passwordChange.getMessage());
        return "jsp/user_ChangePassword";
    }
/*
    @RequestMapping(value = "/changePassword")
    public String currentUserNameSimple(HttpServletRequest request) {
        Principal principal = request.getUserPrincipal();
        User user = userRepository.findByUsername(principal.getName());
        passwordChange.changePassword(user.getId(),user.getPassword());
        return "jsp/user_ChangePassword";
    }
    */
}
