package pl.javastart.contoller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.javastart.model.User;
import pl.javastart.model.UserRole;
import pl.javastart.model.UserRoles;
import pl.javastart.repository.UserRepository;
import pl.javastart.repository.UserRoleRepository;

import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController {


    @RequestMapping("/")
    public String showHomePage(){


        return "home";
    }

    @RequestMapping("/secure")
    public String showSecuredPage(){
        return "secure";
    }
}
