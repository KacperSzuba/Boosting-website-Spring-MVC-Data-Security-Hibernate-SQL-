package pl.javastart.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.javastart.model.entity.User;
import pl.javastart.model.entity.enums.RoleName;
import pl.javastart.repository.UserRepository;
import pl.javastart.repository.UserRoleRepository;
import pl.javastart.model.entity.UserRole;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserCreatorService {
    private String message;

    private String endPoint;

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
        catch (IllegalArgumentException exception2){
            exception2.getCause();
            setUserRegistrationInformation("User with this username already exist");
            return false;
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
            throw new IllegalArgumentException();
        }
        else {
            UserRole userRole = userRoleRepository.getUserRole(RoleName.ROLE_USER);
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

    public String getEndPoint() {
        return endPoint;
    }

    public void setEndPoint(String endPoint) {
        this.endPoint = endPoint;
    }
}
