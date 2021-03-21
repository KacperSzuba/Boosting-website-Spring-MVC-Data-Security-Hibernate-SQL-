package com.BoostingWebsite.email;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;

@Configuration
class EmailServiceConfiguration {

    @Bean
    EmailBusiness emailBusiness(final JavaMailSender emailSender){
        return new EmailBusiness(emailSender);
    }
}
