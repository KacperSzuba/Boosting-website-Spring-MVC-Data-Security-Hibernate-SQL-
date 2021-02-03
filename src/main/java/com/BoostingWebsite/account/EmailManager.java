package com.BoostingWebsite.account;

import com.BoostingWebsite.account.exception.DataMismatchException;
import com.BoostingWebsite.auth.UserTokenFacade;
import com.BoostingWebsite.email.EmailService;
import com.BoostingWebsite.utils.ApplicationSession;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import com.BoostingWebsite.utils.EmailValidator;

import java.util.Collections;

import static com.BoostingWebsite.account.EmailValidator.whetherTheEmailsAreTheSame;

@Service
class EmailManager {
    private final ApplicationSession applicationSession;
    private final EmailService emailService;
    private final UserTokenFacade userTokenFacade;
    private final UserFacade userFacade;

    EmailManager(ApplicationSession applicationSession, EmailService emailService, UserTokenFacade userTokenFacade, UserFacade userFacade) {
        this.applicationSession = applicationSession;
        this.emailService = emailService;
        this.userTokenFacade = userTokenFacade;
        this.userFacade = userFacade;
    }

    void confirmEmail(String contextPath, String token, User user) {
        String url = contextPath + "/register/confirm?id=" + user.getId() + "&token=" + token;
        userTokenFacade.saveOrUpdateToken(token, user);
        emailService.sendEmail(user.getEmail(), "Confirm email", " \r\n" + url);
    }

    String emailTokenConfirmation(Long id, String token) {
        String tempToken = userTokenFacade.validateToken(id, token);
        if (tempToken != null) {
            return tempToken;
        } else {
            User user = userFacade.findUserByToken(token);
            Authentication auth = new UsernamePasswordAuthenticationToken(user, null, Collections.singletonList(new SimpleGrantedAuthority("CONFIRMATION_EMAIL_PRIVILEGE")));
            SecurityContextHolder.getContext().setAuthentication(auth);
            return null;
        }
    }

    void changeEmail(String currentEmail, String email, String confirmEmail) throws DataMismatchException {
        if (checkIfEmailEnteredMatchesCurrent(currentEmail) && checkIfEmailIsCorrect(email, confirmEmail) && checkIfEmailIsNotAlreadyInDatabase(currentEmail)) {
            userFacade.changeEmail(loggedInUser(), email);
        }
    }

    private boolean checkIfEmailIsCorrect(String email, String confirmEmail) throws DataMismatchException {
        if (whetherTheEmailsAreTheSame(email, confirmEmail) && EmailValidator.whetherEmailIsValid(email)) {
            return true;
        }

        throw new DataMismatchException("Your email is wrong or emails are different");
    }

    private boolean checkIfEmailEnteredMatchesCurrent(String currentEmail) throws DataMismatchException {
        if (currentEmail.equals(loggedInUser().getEmail())) {
            return true;
        }

        throw new DataMismatchException("The email you entered does not match the current one");
    }

    private boolean checkIfEmailIsNotAlreadyInDatabase(String currentEmail) throws DataMismatchException {
        if (userFacade.existsUserByEmail(currentEmail)) {
            return true;
        }

        throw new DataMismatchException("The email you entered already exists in our database");
    }

    private UserDto loggedInUser() {
        return applicationSession.getActualUser();
    }
}
