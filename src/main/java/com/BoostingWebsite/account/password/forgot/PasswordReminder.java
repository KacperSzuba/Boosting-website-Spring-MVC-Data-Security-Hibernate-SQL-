package com.BoostingWebsite.account.password.forgot;

import com.BoostingWebsite.account.password.reset.PasswordManager;
import org.springframework.stereotype.Service;
import com.BoostingWebsite.account.user.UserRepository;
import com.BoostingWebsite.validator.EmailValidator;

import java.util.UUID;

@Service
public class PasswordReminder {
    private String passwordRemindMessage;

    private final PasswordManager passwordManager;
    private final UserRepository userRepository;
    private final PasswordResetTokenRepository passwordResetTokenRepository;
    public PasswordReminder(PasswordManager passwordManager, UserRepository userRepository, PasswordResetTokenRepository passwordResetTokenRepository) {
        this.passwordManager = passwordManager;
        this.userRepository = userRepository;
        this.passwordResetTokenRepository = passwordResetTokenRepository;
    }

    public void remindPassword(String email){
        if (whetherEmailIsInDatabase(email)){
            String token = UUID.randomUUID().toString();
            System.out.println(token);
            //  passwordResetTokenRepository.save(new PasswordResetToken(token, userRepository.findByEmail(email).get()));
        }
    }

    private boolean whetherEmailIsInDatabase(String email){
        if(whetherEmailIsValid(email)){
            return userRepository.findByEmail(email).isPresent();
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
