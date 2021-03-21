package com.BoostingWebsite.order.message;

import com.BoostingWebsite.account.SimpleUserDto;
import com.BoostingWebsite.account.UserQueryRepository;
import com.BoostingWebsite.order.message.dto.MessageDTO;

class MessageFactory {
    private final UserQueryRepository userQueryRepository;

    MessageFactory(final UserQueryRepository userQueryRepository) {
        this.userQueryRepository = userQueryRepository;
    }

    Message from(MessageDTO messageDTO){
        SimpleUserDto sender = userQueryRepository.findByUsername(messageDTO.getSenderName());
        SimpleUserDto recipient = userQueryRepository.findByUsername(messageDTO.getRecipientName());

        return Message.restore(new MessageSnapshot(
                0L,
                messageDTO.getContent(),
                sender.getSnapshot(),
                recipient.getSnapshot(),
                null));
    }

    MessageDTO toDto(Message message){
        MessageSnapshot snapshot = message.getSnapshot();
        return MessageDTO.builder()
                .withSenderName(snapshot.getAuthor().getUsername())
                .withRecipientName(snapshot.getRecipient().getUsername())
                .withContent(snapshot.getMessage())
                .build();
    }
}
