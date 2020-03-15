package com.BoostingWebsite.account.registration;

import com.BoostingWebsite.account.token.TokenValidator;
import com.BoostingWebsite.account.user.User;
import com.BoostingWebsite.account.user.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.UUID;

@Controller
public class RegisterController {
    private final UserCreatorService userCreator;
    private final UserRepository userRepository;
    private final EmailConfirmation emailConfirmation;
    private final HttpServletRequest request;
    private final EmailConfirmationToken emailConfirmationToken;

    public RegisterController(UserCreatorService userCreator, UserRepository userRepository, EmailConfirmation emailConfirmation, HttpServletRequest request, TokenValidator tokenValidator, EmailConfirmationToken emailConfirmationToken) {
        this.userCreator = userCreator;
        this.userRepository = userRepository;
        this.emailConfirmation = emailConfirmation;
        this.request = request;
        this.emailConfirmationToken = emailConfirmationToken;
    }

    @GetMapping("/register")
    public String showRegisterPage(Model model){
        User user = new User();
        model.addAttribute("register",user);
        return "accountView/register";
    }

    @PostMapping("/registerForm")
    public String register(@Valid @ModelAttribute("register") User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("message",userCreator.getUserRegistrationInformation());
            return "accountView/register";
        }
        else {
            if (userCreator.createAccount(user)) {
                model.addAttribute("confirmEmailMessage","Log in to the e-mail address provided and confirm your identity.");
                emailConfirmation.confirmEmail(getAppUrl(),generateToken(),userRepository.findByUsername(user.getUsername()));
                return "accountView/login";
            }
            else {
                model.addAttribute("message",userCreator.getUserRegistrationInformation());
                return "accountView/register";
            }
        }
    }

    @GetMapping("/register/confirm")
    public String remind(@RequestParam("id")Long id, @RequestParam("token") String token){
        String confirmEmail = emailConfirmationToken.emailTokenConfirmation(id,token);
        if(confirmEmail !=null){
            return "redirect:/login";
        }
        userRepository.changeUserEnabledStatement(id,true);
        return "redirect:/login";
    }

    private String getAppUrl() {
        return "http://" + this.request.getServerName() + ":" + this.request.getServerPort() + this.request.getContextPath();
    }

    private String generateToken(){
        return UUID.randomUUID().toString();
    }

}
