package com.BoostingWebsite.account.password.remind;

import com.BoostingWebsite.account.token.TokenRecorder;
import com.BoostingWebsite.account.token.UserToken;
import com.BoostingWebsite.account.token.UserTokenRepository;
import com.BoostingWebsite.account.user.User;
import com.BoostingWebsite.account.user.UserRepository;
import com.BoostingWebsite.email.EmailService;
import com.BoostingWebsite.exceptions.DataMismatchException;
import com.BoostingWebsite.validator.EmailValidator;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;
import java.util.UUID;

import static com.BoostingWebsite.account.password.PasswordValidator.isPasswordLengthSufficient;
import static com.BoostingWebsite.account.password.PasswordValidator.whetherThePasswordsAreTheSame;

@Service
class PasswordReminder {
    private String passwordRemindMessage;
    private String email;
    private String password;
    private String confirmPassword;

    private final UserRepository userRepository;
    private final UserTokenRepository userTokenRepository;
    private final HttpServletRequest request;
    private final EmailService emailService;
    private final PasswordEncoder encoder;
    private final TokenRecorder tokenRecorderClass;

    PasswordReminder(UserRepository userRepository, UserTokenRepository userTokenRepository, HttpServletRequest request, EmailService emailService,
                     PasswordEncoder encoder, TokenRecorder tokenRecorderClass) {
        this.userRepository = userRepository;
        this.userTokenRepository = userTokenRepository;
        this.request = request;
        this.emailService = emailService;
        this.encoder = encoder;
        this.tokenRecorderClass = tokenRecorderClass;
    }

    void resetPassword(String password, String confirmPassword) {
        this.password = password;
        this.confirmPassword = confirmPassword;
        try {
            tryToResetPassword();
        }
        catch (DataMismatchException exception){
            exception.printStackTrace();
            setPasswordRemindMessage(exception.getMessage());
        }
    }

    private void tryToResetPassword() throws DataMismatchException {
        if (checkIfPasswordsAreTheSameAndHaveRequiredLength()) {
            User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            userRepository.changePassword(user.getId(), encoder.encode(password));
        }
    }

    private boolean checkIfPasswordsAreTheSameAndHaveRequiredLength() throws DataMismatchException {
        if(isPasswordLengthSufficient(this.password) && whetherThePasswordsAreTheSame(this.password, this.confirmPassword)) {
            return true;
        }
        else {
            throw new DataMismatchException("Your password is too short or passwords are different");
        }
    }

    void remindPassword(String email) {
        this.email = email;
        if (whetherEmailIsInDatabase()) {
            tokenRecorderClass.saveOrUpdateToken(getToken(),user().get());
            User user = user().get();
            UserToken userToken = userTokenRepository.findByUser(user).get();
            String token = userToken.getToken();
            emailService.constructResetTokenEmail(getAppUrl(), token, user);
        }
    }

    private boolean whetherEmailIsInDatabase() {
        return this.whetherEmailIsValid() && user().isPresent();
    }

    private boolean whetherEmailIsValid() {
        return EmailValidator.validateEmail(email);
    }

    private String getToken() {
        return UUID.randomUUID().toString();
    }

    private Optional<User> user() {
        return this.userRepository.findByEmail(email);
    }

    private String getAppUrl() {
        return "http://" + this.request.getServerName() + ":" + this.request.getServerPort() + this.request.getContextPath();
    }

    String getPasswordRemindMessage() {
        return passwordRemindMessage;
    }

    private void setPasswordRemindMessage(String passwordRemindMessage) {
        this.passwordRemindMessage = passwordRemindMessage;
    }
}
