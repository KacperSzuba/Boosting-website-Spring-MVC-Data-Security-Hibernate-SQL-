package com.BoostingWebsite.order.message;

import java.util.List;

interface MessageRepository  {
    List<Message> list(Long id);

    List<Message> getChatMessages(Long senderId, Long recipientId);

    Message save(Message message);
}