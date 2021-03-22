package com.BoostingWebsite.order;

import com.BoostingWebsite.account.AccountCommandHandler;
import com.BoostingWebsite.api.APICommandHandler;
import com.BoostingWebsite.order.message.MessageCommandHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class OrderConfig {
    @Bean
    OrderBoostBusiness orderBoostBusiness(final OrderBoostQueryRepository orderBoostQueryRepository){
        return new OrderBoostBusiness(orderBoostQueryRepository, new LeagueFactory());
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
    OrderFacade orderFacade(final OrderCommandHandler orderCommandHandler, final AccountCommandHandler accountCommandHandler){
        return new OrderFacade(orderCommandHandler, accountCommandHandler);
    }

    @Bean
    OrderHistoryFacade orderHistoryFacade(final OrderCommandHandler orderCommandHandler){
        return new OrderHistoryFacade(orderCommandHandler);
    }

    @Bean
    OrderCommandHandler orderCommandHandler(final OrderBoostBusiness orderBoostBusiness, final OrderExtrasBusiness orderExtrasBusiness){
        return new OrderCommandHandler(orderBoostBusiness, orderExtrasBusiness);
    }

    @Bean
    OrderPreviewFacade orderPreviewFacade(
            final OrderCommandHandler orderCommandHandler,
            final MessageCommandHandler messageCommandHandler,
            final APICommandHandler apiCommandHandler){
        return new OrderPreviewFacade(orderCommandHandler, messageCommandHandler, apiCommandHandler);
    }
}
