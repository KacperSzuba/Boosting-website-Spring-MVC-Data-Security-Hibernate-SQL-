package pl.javastart.contoller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.javastart.model.User;
import pl.javastart.model.UserRole;
import pl.javastart.repository.UserRepository;
import pl.javastart.repository.UserRoleRepository;

import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController {

    @Autowired
    UserRoleRepository userRoleRepository;

    @Autowired
    UserRepository userRepository;

    @RequestMapping("/")
    public String showHomePage(){
        /*
        UserRole userRole = new UserRole("ROLE_USER");
        userRoleRepository.save(userRole);
        List<UserRole> roles = new ArrayList<>();
        roles.add(userRole);
        User user = new User("username2","{noop}test123","email",roles);
        userRepository.save(user);
        */
        return "home";
    }

    @RequestMapping("/secure")
    public String showSecuredPage(){
        System.out.println(userRoleRepository.getUserRole().getRoleName());
        return "secure";
    }
}
