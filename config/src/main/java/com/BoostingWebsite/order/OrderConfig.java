package com.BoostingWebsite.order;

import com.BoostingWebsite.account.UserQueryRepository;
import com.BoostingWebsite.utils.ApplicationSession;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class OrderConfig {
    @Bean
    OrderBoostFacade orderBoostFacade(
            final ApplicationSession applicationSession,
            final OrderBoostQueryRepository orderBoostQueryRepository,
            final OrderExtrasRepository orderExtrasRepository,
            final UserQueryRepository userQueryRepository
    ){
        return new OrderBoostFacade(applicationSession, orderBoostQueryRepository, orderExtrasRepository, userQueryRepository, new LeagueFactory());
    }
}
