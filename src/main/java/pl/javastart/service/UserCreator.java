package pl.javastart.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.javastart.model.UserRoles;
import pl.javastart.model.entity.User;
import pl.javastart.model.entity.UserRole;
import pl.javastart.repository.UserRepository;
import pl.javastart.repository.UserRoleRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserCreator {

    private String message;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;


    public boolean createAccount(User user){
        try {
            tryToCreateAccount(user);
            return true;
        }
        catch (Exception exception){
            exception.getCause();
            setUserRegistrationInformation("Invalid registration");
            return false;
        }
    }

    private void tryToCreateAccount(User user){
        boolean isUserExist = userRepository.existsUserByUsername(user.getUsername());

        if(isUserExist){
            setUserRegistrationInformation("User with this username already exist");

        }
        else {
            UserRole userRole = userRoleRepository.getUserRole(UserRoles.ROLE_USER.toString());
            userRoleRepository.save(userRole);
            List<UserRole> roles = new ArrayList<>();
            roles.add(userRole);
            String password = passwordEncoder.encode(user.getPassword());
            userRepository.save(new User(user.getUsername(),password,true,user.getEmail(),LocalDateTime.now(),roles));
        }
    }

    private void setUserRegistrationInformation(String message){
        this.message = message;
    }

    public String getUserRegistrationInformation(){
        return message;
    }
}
