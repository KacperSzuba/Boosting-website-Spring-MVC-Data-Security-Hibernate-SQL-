package com.BoostingWebsite.account.mail;

import com.BoostingWebsite.account.User;
import com.BoostingWebsite.account.UserFacade;
import com.BoostingWebsite.account.dto.UserDto;
import com.BoostingWebsite.account.exception.DataMismatchException;
import com.BoostingWebsite.auth.TokenRecorder;
import com.BoostingWebsite.auth.TokenValidator;
import com.BoostingWebsite.email.EmailService;
import com.BoostingWebsite.utils.ApplicationSession;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import com.BoostingWebsite.utils.EmailValidator;

import java.util.Collections;

import static com.BoostingWebsite.account.mail.EmailValidator.whetherTheEmailsAreTheSame;

@Service
public class EmailManager {
    private final ApplicationSession applicationSession;
    private final EmailService emailService;
    private final TokenRecorder tokenRecorder;
    private final TokenValidator tokenValidator;
    private final UserFacade userFacade;

    EmailManager(ApplicationSession applicationSession, EmailService emailService, TokenRecorder tokenRecorder, TokenValidator tokenValidator, UserFacade userFacade) {
        this.applicationSession = applicationSession;
        this.emailService = emailService;
        this.tokenRecorder = tokenRecorder;
        this.tokenValidator = tokenValidator;
        this.userFacade = userFacade;
    }

    public void confirmEmail(String contextPath, String token, User user) {
        String url = contextPath + "/register/confirm?id=" + user.getId() + "&token=" + token;
        tokenRecorder.saveOrUpdateToken(token, user);
        emailService.sendEmail(user.getEmail(), "Confirm email", " \r\n" + url);
    }

    public String emailTokenConfirmation(Long id, String token) {
        String tempToken = tokenValidator.validateToken(id, token);
        if (tempToken != null) {
            return tempToken;
        } else {
            User user = userFacade.findUserByToken(token);
            Authentication auth = new UsernamePasswordAuthenticationToken(user, null, Collections.singletonList(new SimpleGrantedAuthority("CONFIRMATION_EMAIL_PRIVILEGE")));
            SecurityContextHolder.getContext().setAuthentication(auth);
            return null;
        }
    }

    public void changeEmail(String currentEmail, String email, String confirmEmail) throws DataMismatchException {
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
