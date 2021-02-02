package com.BoostingWebsite.email;

import com.BoostingWebsite.account.User;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class EmailService {

    private final JavaMailSender emailSender;

    public EmailService(JavaMailSender emailSender) {
        this.emailSender = emailSender;
    }

    public void sendEmail(String to, String subject, String content) {
        tryToSendEmail(to, subject, content);
    }

    private void tryToSendEmail(String to, String subject, String content) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(to);
            message.setSubject(subject);
            message.setText(content);
            emailSender.send(message);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public void constructResetTokenEmail(String contextPath, String token, User user) {
        String url = contextPath + "/account/remind/password/token?id=" + user.getId() + "&token=" + token;
        sendEmail(user.getEmail(), "Reset Password", " \r\n" + url);
    }

}
