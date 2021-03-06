package com.BoostingWebsite.order.message;

import com.BoostingWebsite.account.UserQueryRepository;
import com.BoostingWebsite.order.OrderBoostFacade;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class MessageConfiguration {

    @Bean
    MessageFacade messageFacade(
            final MessageRepository messageRepository,
            final OrderBoostFacade orderBoostFacade,
            final UserQueryRepository userQueryRepository
    ){
        return new MessageFacade(messageRepository, orderBoostFacade, new MessageFactory(userQueryRepository));
    }
}
