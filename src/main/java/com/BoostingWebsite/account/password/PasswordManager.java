package com.BoostingWebsite.account.password;

import com.BoostingWebsite.account.User;
import com.BoostingWebsite.account.UserFacade;
import com.BoostingWebsite.account.dto.UserDto;
import com.BoostingWebsite.account.exception.DataMismatchException;
import com.BoostingWebsite.auth.TokenRecorder;
import com.BoostingWebsite.auth.TokenValidator;
import com.BoostingWebsite.auth.UserToken;
import com.BoostingWebsite.auth.UserTokenRepository;
import com.BoostingWebsite.email.EmailService;
import com.BoostingWebsite.utils.ApplicationSession;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;
import java.util.UUID;

import static com.BoostingWebsite.account.password.PasswordValidator.isPasswordLengthSufficient;
import static com.BoostingWebsite.account.password.PasswordValidator.whetherThePasswordsAreTheSame;
import static com.BoostingWebsite.utils.EmailValidator.whetherEmailIsValid;

@Service
public class PasswordManager {
    private final PasswordEncoder passwordEncoder;
    private final UserFacade userFacade;
    private final ApplicationSession applicationSession;
    private final TokenRecorder tokenRecorder;
    private final TokenValidator tokenValidator;
    private final EmailService emailService;
    private final UserTokenRepository userTokenRepository;

    PasswordManager(PasswordEncoder passwordEncoder, UserFacade userFacade, ApplicationSession applicationSession, TokenRecorder tokenRecorder,
                    TokenValidator tokenValidator, EmailService emailService, UserTokenRepository userTokenRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userFacade = userFacade;
        this.applicationSession = applicationSession;
        this.tokenRecorder = tokenRecorder;
        this.tokenValidator = tokenValidator;
        this.emailService = emailService;
        this.userTokenRepository = userTokenRepository;
    }

    public String validateResetPasswordToken(Long id, String token) {
        String tempToken = tokenValidator.validateToken(id, token);
        if (tempToken != null) {
            return tempToken;
        } else {
            User user = userTokenRepository.findByToken(token).getUser();
            Authentication auth = new UsernamePasswordAuthenticationToken(user, null, Collections.singletonList(
                    new SimpleGrantedAuthority("CHANGE_PASSWORD_PRIVILEGE")));
            SecurityContextHolder.getContext().setAuthentication(auth);
            return null;
        }
    }

    public void resetPassword(String password, String confirmPassword) throws DataMismatchException {
        if (checkIfPasswordsAreTheSameAndHaveRequiredLength(password, confirmPassword)) {
            UserDto user = (UserDto) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            userFacade.changePassword(user, passwordEncoder.encode(password));
        }

        throw new DataMismatchException("An unknown error has occurred!");
    }

    public void remindPassword(String email) throws DataMismatchException {
        Optional<User> user = userFacade.findByEmail(email);

        if (whetherEmailIsValid(email) && user.isPresent()) {
            tokenRecorder.saveOrUpdateToken(getToken(), user.get());
            UserToken userToken = userTokenRepository.findByUser(user.get()).get();
            String token = userToken.getToken();
            emailService.constructResetTokenEmail(applicationSession.getAppUrl(), token, user.get());
        }

        throw new DataMismatchException("This e-mail is invalid or already exists in our database!");
    }

    public String getToken() {
        return UUID.randomUUID().toString();
    }

    public void changePassword(String currentPassword, String password, String confirmPassword) throws DataMismatchException {
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