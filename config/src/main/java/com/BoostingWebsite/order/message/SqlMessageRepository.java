package com.BoostingWebsite.order.message;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

interface SqlMessageRepository extends CrudRepository<MessageSnapshot,Long> {
    @Query(value = "select messages from MessageSnapshot messages where messages.recipient.id =:id or messages.author.id =:id order by date asc")
    List<MessageSnapshot> list(@Param("id") Long id);

    @Query(value = "select messages from MessageSnapshot messages where messages.recipient.id in (:senderId, :recipientId) and messages.author.id in (:senderId, :recipientId) order by date asc")
    List<MessageSnapshot> getChatMessages(@Param("senderId") Long senderId, @Param("recipientId") Long recipientId);

    MessageSnapshot save(MessageSnapshot message);
}
