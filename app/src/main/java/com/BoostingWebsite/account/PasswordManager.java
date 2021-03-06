package com.BoostingWebsite.account;

import com.BoostingWebsite.account.exception.DataMismatchException;
import com.BoostingWebsite.email.EmailService;
import com.BoostingWebsite.utils.ApplicationSession;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collections;
import java.util.Optional;
import java.util.UUID;

import static com.BoostingWebsite.utils.PasswordValidator.isPasswordLengthSufficient;
import static com.BoostingWebsite.utils.PasswordValidator.whetherThePasswordsAreTheSame;
import static com.BoostingWebsite.utils.EmailValidator.whetherEmailIsValid;

class PasswordManager {
    private final PasswordEncoder passwordEncoder;
    private final UserFacade userFacade;
    private final ApplicationSession applicationSession;
    private final UserTokenFacade userTokenFacade;
    private final EmailService emailService;
    private final SimpleUserDtoFactory simpleUserDtoFactory;

    PasswordManager(final PasswordEncoder passwordEncoder, final UserFacade userFacade, final ApplicationSession applicationSession, final UserTokenFacade tokenRecorder,
                    final EmailService emailService, final SimpleUserDtoFactory simpleUserDtoFactory) {
        this.passwordEncoder = passwordEncoder;
        this.userFacade = userFacade;
        this.applicationSession = applicationSession;
        this.userTokenFacade = tokenRecorder;
        this.emailService = emailService;
        this.simpleUserDtoFactory = simpleUserDtoFactory;
    }

    String validateResetPasswordToken(Long id, String token) {
        String tempToken = userTokenFacade.validateToken(id, token);
        if (tempToken != null) {
            return tempToken;
        } else {
            User user = simpleUserDtoFactory.from(userTokenFacade.findByToken(token).getUser());
            Authentication auth = new UsernamePasswordAuthenticationToken(user, null, Collections.singletonList(
                    new SimpleGrantedAuthority("CHANGE_PASSWORD_PRIVILEGE")));
            SecurityContextHolder.getContext().setAuthentication(auth);
            return null;
        }
    }

    void resetPassword(String password, String confirmPassword) throws DataMismatchException {
        if (checkIfPasswordsAreTheSameAndHaveRequiredLength(password, confirmPassword)) {
            UserDto user = (UserDto) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            userFacade.changePassword(user, passwordEncoder.encode(password));
        }

        throw new DataMismatchException("An unknown error has occurred!");
    }

    void remindPassword(String email) throws DataMismatchException {
        Optional<User> user = userFacade.findByEmail(email);

        if (whetherEmailIsValid(email) && user.isPresent()) {
            userTokenFacade.saveOrUpdateToken(getToken(), simpleUserDtoFactory.to(user.get()));
            String token = userTokenFacade.findByUser(simpleUserDtoFactory.to(user.get())).getToken();
            emailService.constructResetTokenEmail(applicationSession.getAppUrl(), token, simpleUserDtoFactory.to(user.get()));
        }

        throw new DataMismatchException("This e-mail is invalid or already exists in our database!");
    }

    String getToken() {
        return UUID.randomUUID().toString();
    }

    void changePassword(String currentPassword, String password, String confirmPassword) throws DataMismatchException {
        if (checkIfPasswordEnteredMatchesCurrent(currentPassword) && checkIfPasswordsAreTheSameAndHaveRequiredLength(password, confirmPassword)) {
            userFacade.changePassword(loggedInUser(), passwordEncoder.encode(password));
        }

        throw new DataMismatchException("An unknown error has occurred!");
    }

    private boolean checkIfPasswordsAreTheSameAndHaveRequiredLength(String password, String confirmPassword) throws DataMismatchException {
        if (isPasswordLengthSufficient(password) && whetherThePasswordsAreTheSame(password, confirmPassword)) {
            return true;
        }

        throw new DataMismatchException("Your password is too short or passwords are different!");
    }

    private UserDto loggedInUser() {
        return applicationSession.getActualUser();
    }

    private boolean checkIfPasswordEnteredMatchesCurrent(String currentPassword) throws DataMismatchException {
        if (passwordEncoder.matches(currentPassword, loggedInUser().getPassword())) {
            return true;
        }

        throw new DataMismatchException("The password you entered does not match the current one!");
    }
}