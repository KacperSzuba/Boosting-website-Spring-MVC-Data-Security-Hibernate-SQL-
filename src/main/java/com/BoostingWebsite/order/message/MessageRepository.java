package com.BoostingWebsite.order.message;

import org.springframework.data.repository.query.Param;

import java.util.List;

interface MessageRepository  {
    List<Message> list(@Param("id") Long id);

    List<Message> getChatMessages(@Param("senderId") Long senderId, @Param("recipientId") Long recipientId);
}