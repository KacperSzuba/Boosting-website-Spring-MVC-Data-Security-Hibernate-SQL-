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

    public static boolean isConfirmPasswordIsValid(String confirmPassword) {
        return confirmPassword.length() >= 7 && confirmPassword.length() <= 20;
    }

    public boolean isAccountCreatedCorrectly(User user, String confirmPassword) {
        this.user = user;
        return checkIfEmailExist() && checkIfUserExists() && checkIfPasswordsAreTheSame(confirmPassword);
    }

    private boolean checkIfUserExists() {
        boolean isUserExist = userRepository.existsUserByUsername(this.user.getUsername());
        if (isUserExist) {
            throw new IllegalArgumentException("User with this username already exist");
        } else {
            return true;
        }
    }

    private boolean checkIfEmailExist() {
        boolean isEmailExist = userRepository.existsUserByEmail(this.user.getEmail());
        if (isEmailExist) {
            throw new IllegalArgumentException("User with this email already exist");
        } else {
            return true;
        }
    }

    private boolean checkIfPasswordsAreTheSame(String confirmPassword) {
        boolean isPasswordsAreTheSame = user.getPassword().equals(confirmPassword);
        if (isPasswordsAreTheSame) {
            return true;
        } else {
            throw new IllegalArgumentException("Passwords don't match");
        }
    }
}
