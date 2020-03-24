package com.BoostingWebsite.account.validator;

import com.BoostingWebsite.account.user.User;
import com.BoostingWebsite.account.user.UserRepository;
import org.springframework.stereotype.Component;

@Component
public class CreationAccountValidator {

    private User user;

    private final UserRepository userRepository;

    public CreationAccountValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean isAccountCreatedCorrectly(User user){
        this.user = user;
        return checkIfEmailExist() && checkIfUserExists();
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
}
