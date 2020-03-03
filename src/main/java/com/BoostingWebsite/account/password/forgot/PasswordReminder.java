package com.BoostingWebsite.account.password.forgot;

import com.BoostingWebsite.account.password.reset.PasswordManager;
import org.springframework.stereotype.Service;
import com.BoostingWebsite.account.user.UserRepository;
import com.BoostingWebsite.validator.EmailValidator;

@Service
public class PasswordReminder {
    private String passwordRemindMessage;

    private final PasswordManager passwordManager;
    private final UserRepository userRepository;
    public PasswordReminder(PasswordManager passwordManager, UserRepository userRepository) {
        this.passwordManager = passwordManager;
        this.userRepository = userRepository;
    }

    public void remindPassword(String email){
        if (whetherEmailIsInDatabase(email)){

        }
    }

    private boolean whetherEmailIsInDatabase(String email){
        if(whetherEmailIsValid(email)){
            return userRepository.existsUserByEmail(email);
        }
        return false;
    }

    private boolean whetherEmailIsValid(String email){
        return EmailValidator.isValid(email);
    }

    public String getPasswordRemindMessage() {
        return passwordRemindMessage;
    }

    private void setPasswordRemindMessage(String passwordRemindMessage) {
        this.passwordRemindMessage = passwordRemindMessage;
    }
}
