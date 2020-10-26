package com.BoostingWebsite.account.password.reset;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.BoostingWebsite.account.user.ActualUser;
import com.BoostingWebsite.account.user.User;
import com.BoostingWebsite.account.user.UserRepository;
import com.BoostingWebsite.exceptions.DataMismatchException;

import static com.BoostingWebsite.account.password.PasswordValidator.isPasswordLengthSufficient;
import static com.BoostingWebsite.account.password.PasswordValidator.whetherThePasswordsAreTheSame;

@Service
class PasswordManager {
    private String message;
    private String password;
    private String confirmPassword;

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final ActualUser actualUser;

    PasswordManager(PasswordEncoder passwordEncoder, UserRepository userRepository, ActualUser actualUser) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.actualUser = actualUser;
    }

    void changePassword(String currentPassword, String password, String confirmPassword) {
        this.password = password;
        this.confirmPassword = confirmPassword;
        try {
            whetherPasswordCanBeChanged(currentPassword);
        } catch (DataMismatchException exception) {
            setMessage(exception.getMessage());
        }
    }

    private void whetherPasswordCanBeChanged(String currentPassword) throws DataMismatchException {
        if (checkIfPasswordEnteredMatchesCurrent(currentPassword) && checkIfPasswordsAreTheSameAndHaveRequiredLength()) {
            tryToChangePassword();
        }
    }

    private boolean checkIfPasswordsAreTheSameAndHaveRequiredLength() throws DataMismatchException {
        if (isPasswordLengthSufficient(this.password) && whetherThePasswordsAreTheSame(this.password, this.confirmPassword)) {
            return true;
        } else {
            throw new DataMismatchException("Your password is too short or passwords are different");
        }
    }

    private void tryToChangePassword() {
        userRepository.changePassword(loggedInUser().getId(), passwordEncoder.encode(this.password));
        setMessage("Your new password is : " + this.password);
    }

    private User loggedInUser() {
        return actualUser.getActualUser();
    }

    private boolean checkIfPasswordEnteredMatchesCurrent(String currentPassword) throws DataMismatchException {
        if (passwordEncoder.matches(currentPassword, loggedInUser().getPassword())) {
            return true;
        } else {
            throw new DataMismatchException("The password you entered does not match the current one");
        }
    }

    public String getMessage() {
        return message;
    }

    private void setMessage(String message) {
        this.message = message;
    }
}