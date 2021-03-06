package com.BoostingWebsite.email;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;

@Configuration
class EmailServiceConfiguration {

    @Bean
    EmailService emailService(final JavaMailSender emailSender){
        return new EmailService(emailSender);
    }
}
