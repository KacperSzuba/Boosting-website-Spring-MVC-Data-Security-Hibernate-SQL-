package com.BoostingWebsite.order.message;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

interface SqlMessageRepository extends MessageRepository, CrudRepository<Message,Long> {
    @Query(value = "select messages from Message messages where messages.recipient.id =:id or messages.author.id =:id order by date asc")
    List<Message> list(@Param("id") Long id);

    @Query(value = "select messages from Message messages where messages.recipient.id in (:senderId, :recipientId) and messages.author.id in (:senderId, :recipientId) order by date asc")
    List<Message> getChatMessages(@Param("senderId") Long senderId, @Param("recipientId") Long recipientId);

    Message save(Message message);
}
