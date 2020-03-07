package com.BoostingWebsite.email;

import com.BoostingWebsite.account.user.User;
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
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(content);
        emailSender.send(message);
    }

    public void constructResetTokenEmail(String contextPath, String token, User user) {
        String url = contextPath + "/account/remindPassword?id=" + user.getId() + "&token=" + token;
        sendEmail(user.getEmail(),"Reset Password", " \r\n" + url);
    }
}
