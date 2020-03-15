package com.BoostingWebsite.account.registration;

import com.BoostingWebsite.account.token.TokenRecorder;
import com.BoostingWebsite.account.user.User;
import com.BoostingWebsite.email.EmailService;
import org.springframework.stereotype.Component;

@Component
public class EmailConfirmation {

    private final EmailService emailService;
    private final TokenRecorder tokenRecorderComponent;

    public EmailConfirmation(EmailService emailService, TokenRecorder tokenRecorderComponent) {
        this.emailService = emailService;
        this.tokenRecorderComponent = tokenRecorderComponent;
    }

    void confirmEmail(String contextPath, String token, User user) {
        String url = contextPath + "/register/confirm?id=" + user.getId() + "&token=" + token;
        tokenRecorderComponent.saveOrUpdateToken(token,user);
        emailService.sendEmail(user.getEmail(),"Confirm email", " \r\n" + url);
    }
}
