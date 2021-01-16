package com.BoostingWebsite.account.registration;

import com.BoostingWebsite.account.user.User;
import com.BoostingWebsite.account.user.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.UUID;

import static com.BoostingWebsite.account.validator.CreationAccountValidator.isConfirmPasswordIsValid;

@Controller
@RequestMapping("/register")
class RegisterController {

    private final UserCreatorService userCreator;
    private final UserRepository userRepository;
    private final EmailConfirmation emailConfirmation;
    private final HttpServletRequest request;
    private final EmailConfirmationToken emailConfirmationToken;

    RegisterController(UserCreatorService userCreator, UserRepository userRepository, EmailConfirmation emailConfirmation, HttpServletRequest request,
                              EmailConfirmationToken emailConfirmationToken) {
        this.userCreator = userCreator;
        this.userRepository = userRepository;
        this.emailConfirmation = emailConfirmation;
        this.request = request;
        this.emailConfirmationToken = emailConfirmationToken;
    }

    @GetMapping
    String registerPage(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "account/register";
    }

    @PostMapping
    String register(@Valid @ModelAttribute("user") User user, BindingResult result, @RequestParam("confirmPassword") String confirmPassword, Model model) {
        if (result.hasErrors() || isConfirmPasswordIsValid(confirmPassword)) {
            model.addAttribute("confirmPasswordErrorMessage", "Confirmation password length should be between 7 and 20 letters");
            return "account/register";
        } else {
            if (userCreator.createAccount(user, confirmPassword)) {
                model.addAttribute("confirmEmailMessage", "Log in to the e-mail address provided and confirm your identity.");
                emailConfirmation.confirmEmail(getAppUrl(), generateToken(), userRepository.findByUsername(user.getUsername()));
                return "account/login";
            } else {
                return "account/register";
            }
        }
    }

    @GetMapping("/confirm")
    String confirmEmail(@RequestParam("id") Long id, @RequestParam("token") String token) {
        String confirmEmail = emailConfirmationToken.emailTokenConfirmation(id, token);
        if (confirmEmail != null) {
            return "redirect:/login";
        }
        userRepository.changeUserEnabledStatement(id, true);
        return "redirect:/login";
    }

    private String getAppUrl() {
        return "http://" + this.request.getServerName() + ":" + this.request.getServerPort() + this.request.getContextPath();
    }

    private String generateToken() {
        return UUID.randomUUID().toString();
    }
}
