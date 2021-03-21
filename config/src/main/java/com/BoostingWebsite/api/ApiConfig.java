package com.BoostingWebsite.api;

import com.BoostingWebsite.order.OrderBoostBusiness;
import com.BoostingWebsite.order.exception.OrderBoostNotFoundException;
import com.BoostingWebsite.utils.ConstBusiness;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.annotation.SessionScope;

@Configuration
class ApiConfig {

    @Bean
    @SessionScope
    LeagueOfLegendsAPIConnector leagueOfLegendsAPIConnector(final OrderBoostBusiness orderBoostBusiness, final ConstBusiness constBusiness) throws OrderBoostNotFoundException {
        return new LeagueOfLegendsAPIConnector(orderBoostBusiness, constBusiness);
    }

    @Bean
    LeagueOfLegendsAPIBusiness leagueOfLegendsAPIFacade(final LeagueOfLegendsAPIConnector leagueOfLegendsAPIConnector){
        return new LeagueOfLegendsAPIBusiness(leagueOfLegendsAPIConnector);
    }
}
