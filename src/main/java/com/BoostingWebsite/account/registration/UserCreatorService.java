package com.BoostingWebsite.account.registration;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.BoostingWebsite.account.user.User;
import com.BoostingWebsite.account.roles.RoleName;
import com.BoostingWebsite.account.user.UserRepository;
import com.BoostingWebsite.account.roles.UserRoleRepository;
import com.BoostingWebsite.account.roles.UserRole;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
class UserCreatorService {
    private String message;

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;

    UserCreatorService(PasswordEncoder passwordEncoder, UserRepository userRepository, UserRoleRepository userRoleRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
    }

    boolean createAccount(User user){
        try {
            tryToCreateAccount(user);
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

    private void tryToCreateAccount(User user){
        if (checkIfUserExists(user) && checkIfEmailExist(user)) {
            UserRole userRole = userRoleRepository.getUserRole(RoleName.ROLE_USER);
            userRoleRepository.save(userRole);
            List<UserRole> roles = new ArrayList<>();
            roles.add(userRole);
            String password = passwordEncoder.encode(user.getPassword());
            userRepository.save(new User(user.getUsername(), password, true, user.getEmail(), LocalDateTime.now(), roles));
        }
    }

    private boolean checkIfUserExists(User user){
        boolean isUserExist = userRepository.existsUserByUsername(user.getUsername());
        if(isUserExist){
            throw new IllegalArgumentException("User with this username already exist");
        }
        else {
            return true;
        }
    }

    private boolean checkIfEmailExist(User user){
        boolean isEmailExist = userRepository.existsUserByEmail(user.getEmail());
        if (isEmailExist){
            throw new IllegalArgumentException("User with this email already exist");
        }
        else {
            return true;
        }
    }

    private void setUserRegistrationInformation(String message){
        this.message = message;
    }

    String getUserRegistrationInformation(){
        return message;
    }
}
