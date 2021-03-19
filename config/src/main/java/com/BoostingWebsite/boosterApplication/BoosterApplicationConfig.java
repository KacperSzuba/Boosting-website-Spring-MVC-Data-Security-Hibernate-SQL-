package com.BoostingWebsite.boosterApplication;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class BoosterApplicationConfig {

    @Bean
    BoosterApplicationFacade boosterApplicationFacade(BoosterApplicationBusiness boosterApplicationBusiness){
        return new BoosterApplicationFacade(boosterApplicationBusiness, new BoosterApplicationFactory());
    }

    @Bean
    BoosterApplicationBusiness boosterApplicationBusiness(BoosterApplicationRepository repository){
        return new BoosterApplicationBusiness(repository);
    }
}
