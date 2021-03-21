package com.BoostingWebsite.order.message;

import com.BoostingWebsite.account.UserQueryRepository;
import com.BoostingWebsite.order.OrderBoostBusiness;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class MessageConfig {

    @Bean
    MessageBusiness messageBusiness(
            final MessageRepository messageRepository,
            final OrderBoostBusiness orderBoostBusiness,
            final UserQueryRepository userQueryRepository
    ){
        return new MessageBusiness(messageRepository, orderBoostBusiness, new MessageFactory(userQueryRepository));
    }
}
