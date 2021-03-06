package com.BoostingWebsite.utils;

import com.BoostingWebsite.account.UserFacade;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.http.HttpServletRequest;

@Configuration
class UtilsConfiguration {
    @Bean
    ApplicationSession applicationSession(final UserFacade userFacade, final HttpServletRequest request){
        return new ApplicationSession(userFacade, request);
    }

    @Bean
    ConstFacade constFacade(final ConstRepository constRepository){
        return new ConstFacade(constRepository);
    }
}
