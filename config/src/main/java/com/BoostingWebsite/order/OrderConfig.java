package com.BoostingWebsite.order;

import com.BoostingWebsite.account.UserBusiness;
import com.BoostingWebsite.api.LeagueOfLegendsAPIBusiness;
import com.BoostingWebsite.order.message.MessageBusiness;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class OrderConfig {
    @Bean
    OrderBoostBusiness orderBoostBusiness(
            final UserBusiness userBusiness,
            final OrderBoostQueryRepository orderBoostQueryRepository){
        return new OrderBoostBusiness(userBusiness, orderBoostQueryRepository, new LeagueFactory());
    }

    @Bean
    OrderExtrasBusiness orderExtrasBusiness(final OrderExtrasRepository orderExtrasRepository){
        return new OrderExtrasBusiness(orderExtrasRepository);
    }

    @Bean
    FreeOrdersFacade freeOrdersFacade(final OrderBoostBusiness orderBoostBusiness){
        return new FreeOrdersFacade(orderBoostBusiness);
    }

    @Bean
    OrderFacade orderFacade(final OrderBoostBusiness orderBoostBusiness, final OrderExtrasBusiness orderExtrasBusiness){
        return new OrderFacade(orderBoostBusiness, orderExtrasBusiness);
    }

    @Bean
    OrderHistoryFacade orderHistoryFacade(final OrderBoostBusiness orderBoostBusiness){
        return new OrderHistoryFacade(orderBoostBusiness);
    }

    @Bean
    OrderPreviewFacade orderPreviewFacade(
            final OrderBoostBusiness orderBoostBusiness,
            final LeagueOfLegendsAPIBusiness leagueOfLegendsAPIBusiness,
            final MessageBusiness messageBusiness
            ){
        return new OrderPreviewFacade(orderBoostBusiness, leagueOfLegendsAPIBusiness, messageBusiness);
    }
}
