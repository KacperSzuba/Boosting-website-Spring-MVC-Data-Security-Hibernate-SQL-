package pl.javastart.manage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.javastart.model.entity.User;
import pl.javastart.repository.UserRepository;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@Component
public class ActualUser {
    @Autowired
    private UserRepository userRepository;

    public User getActualUser(HttpServletRequest request){
        Principal principal = request.getUserPrincipal();
        return userRepository.findByUsername(principal.getName());
    }
}
