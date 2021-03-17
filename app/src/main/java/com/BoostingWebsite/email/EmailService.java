package com.BoostingWebsite.email;

import com.BoostingWebsite.account.SimpleUserDto;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

public class EmailService {

    private final JavaMailSender emailSender;

    public EmailService(final JavaMailSender emailSender) {
        this.emailSender = emailSender;
    }

    public void sendEmail(String to, String subject, String content) {
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

    public void constructResetTokenEmail(String contextPath, String token, SimpleUserDto user) {
        String url = contextPath + "/account/remind/password/token?id=" + user.getSnapshot().getId() + "&token=" + token;
        sendEmail(user.getSnapshot() .getEmail(), "Reset Password", " \r\n" + url);
    }
}
