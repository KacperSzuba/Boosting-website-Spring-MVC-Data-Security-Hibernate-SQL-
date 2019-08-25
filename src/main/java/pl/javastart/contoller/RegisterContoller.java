package pl.javastart.contoller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.javastart.model.User;
import pl.javastart.model.UserRole;
import pl.javastart.model.UserRoles;
import pl.javastart.repository.UserRepository;
import pl.javastart.repository.UserRoleRepository;

import java.util.ArrayList;
import java.util.List;

@Controller
public class RegisterContoller {

    @Autowired
    UserRoleRepository userRoleRepository;

    @Autowired
    UserRepository userRepository;

    @GetMapping("/register")
    public String showRegisterPage(){

        return "register";
    }

    @PostMapping("/registerForm")
    public String register(@RequestParam String username,@RequestParam String password,
                                   @RequestParam String email){

        UserRole userRole = userRoleRepository.getUserRole(UserRoles.ROLE_USER.toString());
        userRoleRepository.save(userRole);
        List<UserRole> roles = new ArrayList<>();
        roles.add(userRole);
        User user = new User(username,"{noop}"+password,email,roles);
        userRepository.save(user);

        return "account";
    }


}
