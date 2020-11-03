package com.BoostingWebsite.message;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MessageRepository extends CrudRepository<Message,Long> {
    @Query(value = "select messages from Message messages where (messages.recipient.id=:id and messages.author.id not in(select messages.recipient.id from messages where messages.author.id=:id group by messages.recipient.id)) or messages.author.id=:id  group by messages.author.id,messages.recipient.id order by messages.date desc ")
    List<Message> listOfRecipients(@Param("id")Long id);

    @Query(value = "select messages from Message messages where messages.recipient.id =:id or messages.author.id =:id order by date asc")
    List<Message> list(@Param("id") Long id);
}