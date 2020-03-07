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
    private User user;

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;

    UserCreatorService(PasswordEncoder passwordEncoder, UserRepository userRepository, UserRoleRepository userRoleRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
    }

    boolean createAccount(User user){
        this.user = user;
        try {
            tryToCreateAccount();
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

    private void tryToCreateAccount(){
        if (checkIfUserExists() && checkIfEmailExist()) {
            UserRole userRole = userRoleRepository.getUserRole(RoleName.ROLE_USER);
            userRoleRepository.save(userRole);
            List<UserRole> roles = new ArrayList<>();
            roles.add(userRole);
            String password = passwordEncoder.encode(this.user.getPassword());
            userRepository.save(new User(this.user.getUsername(), password, true, this.user.getEmail(), LocalDateTime.now(), roles));
        }
    }

    private boolean checkIfUserExists(){
        boolean isUserExist = userRepository.existsUserByUsername(this.user.getUsername());
        if(isUserExist){
            throw new IllegalArgumentException("User with this username already exist");
        }
        else {
            return true;
        }
    }

    private boolean checkIfEmailExist(){
        boolean isEmailExist = userRepository.existsUserByEmail(this.user.getEmail());
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
