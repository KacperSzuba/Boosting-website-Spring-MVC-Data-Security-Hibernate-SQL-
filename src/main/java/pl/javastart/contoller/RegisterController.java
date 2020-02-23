package pl.javastart.contoller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.javastart.model.entity.user.User;
import pl.javastart.service.UserCreatorService;

import javax.validation.Valid;

@Controller
public class RegisterController {

    private final UserCreatorService userCreator;

    public RegisterController(UserCreatorService userCreator) {
        this.userCreator = userCreator;
    }

    @GetMapping("/register")
    public String showRegisterPage(Model model){
        User user = new User();
        model.addAttribute("register",user);
        return "accountView/register";
    }

    @PostMapping("/registerForm")
    public String register(@Valid @ModelAttribute("register") User user, BindingResult result,Model model) {
        if (result.hasErrors()) {
            model.addAttribute("message",userCreator.getUserRegistrationInformation());
            return "accountView/register";
        } else {
            if (userCreator.createAccount(user)) {
                return "redirect:/login";
            } else {
                model.addAttribute("message",userCreator.getUserRegistrationInformation());
                return "accountView/register";
            }
        }
    }
}
