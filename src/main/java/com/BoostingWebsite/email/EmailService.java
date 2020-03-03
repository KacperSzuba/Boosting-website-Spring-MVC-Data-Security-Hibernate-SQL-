package com.BoostingWebsite.email;

public interface EmailService {
    void sendEmail(String to, String subject, String content);
}
