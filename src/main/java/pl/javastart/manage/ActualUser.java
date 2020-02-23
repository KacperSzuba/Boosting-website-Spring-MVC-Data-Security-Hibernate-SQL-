package pl.javastart.manage;

import org.springframework.stereotype.Component;
import pl.javastart.model.entity.user.User;
import pl.javastart.repository.user.UserRepository;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@Component
public class ActualUser {

    private final UserRepository userRepository;

    public ActualUser(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getActualUser(HttpServletRequest request){
        Principal principal = request.getUserPrincipal();
        return userRepository.findByUsername(principal.getName());
    }
}
