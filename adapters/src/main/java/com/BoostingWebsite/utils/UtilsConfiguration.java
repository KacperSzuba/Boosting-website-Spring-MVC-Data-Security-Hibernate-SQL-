package com.BoostingWebsite.utils;

import com.BoostingWebsite.account.UserBusiness;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.annotation.SessionScope;

import javax.servlet.http.HttpServletRequest;

@Configuration
class UtilsConfiguration {
    @Bean
    @SessionScope
    ApplicationSession applicationSession(final UserBusiness userBusiness, final HttpServletRequest request){
        return new ApplicationSession(userBusiness, request);
    }

    @Bean
    ConstFacade constFacade(final ConstRepository constRepository){
        return new ConstFacade(constRepository);
    }
}
