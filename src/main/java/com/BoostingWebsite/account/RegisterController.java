package com.BoostingWebsite.account;

import com.BoostingWebsite.utils.ApplicationSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

import static com.BoostingWebsite.account.UserValidator.isConfirmPasswordIsValid;

@Controller
@RequestMapping("/register")
class RegisterController {

    private final UserFacade userFacade;
    private final EmailManager emailManager;
    private final ApplicationSession applicationSession;
    private final UserValidator userValidator;

    RegisterController(final UserFacade userFacade, final EmailManager emailManager, final ApplicationSession applicationSession, final UserValidator userValidator) {
        this.userFacade = userFacade;
        this.emailManager = emailManager;
        this.applicationSession = applicationSession;
        this.userValidator = userValidator;
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
            if (userValidator.canCreateAccount(user, confirmPassword)) {
                userFacade.createAccount(user);
                model.addAttribute("confirmEmailMessage", "Login to the e-mail address provided and confirm your identity.");
                emailManager.confirmEmail(applicationSession.getAppUrl(), generateToken(), userFacade.findByUsername(user.getUsername()));
                return "account/login";
            } else {
                return "account/register";
            }
        }
    }

    @GetMapping("/confirm")
    String confirmEmail(@RequestParam("id") Long id, @RequestParam("token") String token) {
        String confirmEmail = emailManager.emailTokenConfirmation(id, token);
        if (confirmEmail != null) {
            return "redirect:/login";
        }

        userFacade.setEnabled(id);

        return "redirect:/login";
    }

    private String generateToken() {
        return UUID.randomUUID().toString();
    }
}
