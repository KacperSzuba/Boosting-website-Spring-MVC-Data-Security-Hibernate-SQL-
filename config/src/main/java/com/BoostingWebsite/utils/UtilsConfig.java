package com.BoostingWebsite.utils;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.annotation.SessionScope;

import javax.servlet.http.HttpServletRequest;

@Configuration
class UtilsConfig {
    @Bean
    @SessionScope
    ApplicationSession applicationSession(final HttpServletRequest request){
        return new ApplicationSession(request);
    }

    @Bean
    ConstBusiness constBusiness(final ConstRepository constRepository){
        return new ConstBusiness(constRepository);
    }
}
