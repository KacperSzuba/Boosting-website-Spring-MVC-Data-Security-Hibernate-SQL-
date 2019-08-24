package pl.javastart.contoller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.javastart.model.User;
import pl.javastart.repository.UserRepository;

@Controller
public class HomeController {

    @Autowired
    UserRepository userRepository;

    @RequestMapping("/")
    public String showHomePage(){
        return "home";
    }

    @RequestMapping("/secure")
    public String showSecuredPage(){

        User user = new User("user","{noop}pass","email");
        userRepository.save(user);

        return "secure";
    }
}
