package pl.javastart.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.javastart.model.entity.user.User;
import pl.javastart.model.enums.RoleName;
import pl.javastart.repository.user.UserRepository;
import pl.javastart.repository.user.UserRoleRepository;
import pl.javastart.model.entity.user.UserRole;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserCreatorService {
    private String message;

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;

    public UserCreatorService(PasswordEncoder passwordEncoder, UserRepository userRepository, UserRoleRepository userRoleRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
    }

    public boolean createAccount(User user){
        try {
            checkIfUserExists(user);
            return true;
        }
        catch (IllegalArgumentException exception){
            exception.printStackTrace();
            setUserRegistrationInformation(exception.getMessage());
            return false;
        }
        catch (Exception exception){
            exception.printStackTrace();
            setUserRegistrationInformation("Invalid registration");
            return false;
        }
    }

    private void checkIfUserExists(User user){
        boolean isUserExist = userRepository.existsUserByUsername(user.getUsername());
        if(isUserExist){
            //zrobić swój wyjątek
            throw new IllegalArgumentException("User with this username already exist");
        }
        else {
            tryToCreateAccount(user);
        }
    }

    private void tryToCreateAccount(User user){
        UserRole userRole = userRoleRepository.getUserRole(RoleName.ROLE_USER);
        userRoleRepository.save(userRole);
        List<UserRole> roles = new ArrayList<>();
        roles.add(userRole);
        String password = passwordEncoder.encode(user.getPassword());
        userRepository.save(new User(user.getUsername(),password,true,user.getEmail(),LocalDateTime.now(),roles));
    }

    private void setUserRegistrationInformation(String message){
        this.message = message;
    }

    public String getUserRegistrationInformation(){
        return message;
    }
}
