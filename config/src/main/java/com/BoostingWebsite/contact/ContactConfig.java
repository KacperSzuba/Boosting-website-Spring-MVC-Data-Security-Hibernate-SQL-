package com.BoostingWebsite.contact;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class ContactConfig {
    @Bean
    ContactBusiness contactBusiness(final ContactRepository contactRepository){
        return new ContactBusiness(contactRepository);
    }

    @Bean ContactFacade contactFacade(final ContactBusiness contactBusiness){
        return new ContactFacade(contactBusiness, new ContactFactory());
    }
}
