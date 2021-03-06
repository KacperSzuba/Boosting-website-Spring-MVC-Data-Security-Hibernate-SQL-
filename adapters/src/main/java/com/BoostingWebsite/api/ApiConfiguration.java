package com.BoostingWebsite.api;

import com.BoostingWebsite.order.OrderBoostFacade;
import com.BoostingWebsite.order.exception.OrderBoostNotFoundException;
import com.BoostingWebsite.utils.ConstFacade;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.annotation.SessionScope;

@Configuration
class ApiConfiguration {

    @Bean
    @SessionScope
    LeagueOfLegendsAPIConnector leagueOfLegendsAPIConnector(final OrderBoostFacade orderBoostFacade, final ConstFacade constFacade) throws OrderBoostNotFoundException {
        return new LeagueOfLegendsAPIConnector(orderBoostFacade, constFacade);
    }

    @Bean
    LeagueOfLegendsAPIFacade leagueOfLegendsAPIFacade(final LeagueOfLegendsAPIConnector leagueOfLegendsAPIConnector){
        return new LeagueOfLegendsAPIFacade(leagueOfLegendsAPIConnector);
    }
}
