package com.BoostingWebsite.email;

import com.BoostingWebsite.account.SimpleUserDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

public class EmailBusiness {
    private static final Logger logger  = LoggerFactory.getLogger(EmailBusiness.class);

    private final JavaMailSender emailSender;

    public EmailBusiness(final JavaMailSender emailSender) {
        this.emailSender = emailSender;
    }

    public void sendEmail(String to, String subject, String content) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(to);
            message.setSubject(subject);
            message.setText(content);
            emailSender.send(message);
        } catch (Exception ex) {
            logger.error("Error during sending mail!", ex);
        }
    }

    public void constructResetTokenEmail(String contextPath, String token, SimpleUserDto user) {
        String url = contextPath + "/account/remind/password/token?id=" + user.getSnapshot().getId() + "&token=" + token;
        sendEmail(user.getSnapshot() .getEmail(), "Reset Password", " \r\n" + url);
    }
}
