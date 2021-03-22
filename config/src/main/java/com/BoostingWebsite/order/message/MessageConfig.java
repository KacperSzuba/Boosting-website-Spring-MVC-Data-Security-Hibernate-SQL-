package com.BoostingWebsite.order.message;

import com.BoostingWebsite.account.UserQueryRepository;
import com.BoostingWebsite.order.OrderCommandHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class MessageConfig {

    @Bean
    MessageBusiness messageBusiness(final MessageRepository messageRepository, final UserQueryRepository userQueryRepository){
        return new MessageBusiness(messageRepository , new MessageFactory(userQueryRepository));
    }

    @Bean
    MessageCommandHandler messageCommandHandler(final MessageBusiness messageBusiness, final OrderCommandHandler orderCommandHandler){
        return new MessageCommandHandler(messageBusiness, orderCommandHandler);
    }
}
