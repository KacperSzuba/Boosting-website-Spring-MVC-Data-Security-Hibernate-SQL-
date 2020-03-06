package com.BoostingWebsite.account.password.forgot;

import com.BoostingWebsite.account.password.reset.PasswordManager;
import com.BoostingWebsite.account.user.User;
import com.BoostingWebsite.account.user.UserRepository;
import com.BoostingWebsite.email.EmailServiceImpl;
import com.BoostingWebsite.exceptions.DataMismatchException;
import com.BoostingWebsite.validator.EmailValidator;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Optional;
import java.util.UUID;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

import static com.BoostingWebsite.account.password.PasswordValidator.isPasswordLengthSufficient;
import static com.BoostingWebsite.account.password.PasswordValidator.whetherThePasswordsAreTheSame;

@Service
public class PasswordReminder {
    private String passwordRemindMessage;
    private String email;

    private final UserRepository userRepository;
    private final PasswordResetTokenRepository passwordResetTokenRepository;
    private final HttpServletRequest request;
    private final EmailServiceImpl emailService;
    private final PasswordEncoder encoder;

    public PasswordReminder(UserRepository userRepository, PasswordResetTokenRepository passwordResetTokenRepository, HttpServletRequest request, EmailServiceImpl emailService, PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.passwordResetTokenRepository = passwordResetTokenRepository;
        this.request = request;
        this.emailService = emailService;
        this.encoder = encoder;
    }

    public void resetPassword(String password, String repeatPassword) {
        try {
            tryToResetPassword(password,repeatPassword);
        }
        catch (DataMismatchException exception){
            exception.printStackTrace();
            setPasswordRemindMessage(exception.getMessage());
        }
    }

    private void tryToResetPassword(String password, String repeatPassword) throws DataMismatchException {
        if (checkIfPasswordsAreTheSameAndHaveRequiredLength(password, repeatPassword)) {
            User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            userRepository.changePassword(user.getId(), encoder.encode(password));
        }
    }

    private boolean checkIfPasswordsAreTheSameAndHaveRequiredLength(String password, String repeatPassword) throws DataMismatchException {
        if(isPasswordLengthSufficient(password) && whetherThePasswordsAreTheSame(password,repeatPassword)) {
            return true;
        }
        else {
            throw new DataMismatchException("Your password is too short or passwords are different");
        }
    }

    public void remindPassword(String email) {
        this.email = email;
        System.out.println(getAppUrl());
        if (whetherEmailIsInDatabase()) {
            saveOrUpdateToken();
            emailService.constructResetTokenEmail(getAppUrl(),passwordResetTokenRepository.findByUser(user().get()).get().getToken(),user().get());
        }
    }

    public String validatePasswordResetToken(long id, String token) {
        PasswordResetToken passToken =
                passwordResetTokenRepository.findByToken(token);
        if ((passToken == null) || (passToken.getUser()
                .getId() != id)) {
            return "invalid";
        }

        Calendar cal = Calendar.getInstance();
        if ((passToken.getExpiryDate()
                .getTime() - cal.getTime()
                .getTime()) <= 0) {
            return "expired";
        }

        User user = passToken.getUser();
        Authentication auth = new UsernamePasswordAuthenticationToken(
                user, null, Arrays.asList(
                new SimpleGrantedAuthority("CHANGE_PASSWORD_PRIVILEGE")));
        SecurityContextHolder.getContext().setAuthentication(auth);
        return null;
    }

    private void saveOrUpdateToken() {
        if (checkIfUserHasTokenGenerated()) {
            saveToken();
        }
        else {
            updateToken();
        }
    }

    private void updateToken() {
        this.passwordResetTokenRepository.changeToken(getToken(), user().get());
    }

    private void saveToken() {
        this.passwordResetTokenRepository.save(new PasswordResetToken(getToken(), user().get()));
    }

    private boolean checkIfUserHasTokenGenerated() {
        return this.passwordResetTokenRepository.findByUser(user().get()).isEmpty();
}

    private boolean whetherEmailIsInDatabase() {
        return this.whetherEmailIsValid() && user().isPresent();
    }

    private boolean whetherEmailIsValid() {
        return EmailValidator.isValid(email);
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

    public String getPasswordRemindMessage() {
        return passwordRemindMessage;
    }

    private void setPasswordRemindMessage(String passwordRemindMessage) {
        this.passwordRemindMessage = passwordRemindMessage;
    }
}
