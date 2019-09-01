package pl.javastart.contoller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.javastart.model.entity.User;
import pl.javastart.service.UserCreator;

import javax.validation.Valid;

@Controller
public class RegisterController {

    @Autowired
    private UserCreator userCreator;

    @GetMapping("/register")
    public String showRegisterPage(Model model){
        User user = new User();
        model.addAttribute("register",user);
        return "jsp/register";
    }

    @PostMapping("/registerForm")
    public String register(@Valid @ModelAttribute("register") User user, BindingResult result) {
        if (result.hasErrors()) {
            return "jsp/register";
        } else {
            if (userCreator.createAccount(user)) {
                return "redirect:/login";
            } else {
                return "jsp/register";
            }
        }
    }
}
