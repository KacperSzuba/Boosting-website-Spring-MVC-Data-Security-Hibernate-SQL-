package com.BoostingWebsite.order.message.dto;

import com.BoostingWebsite.account.User;
import org.springframework.data.annotation.PersistenceConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "messages")
public class SimpleMessageDto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String message;
    @Column(name = "message_sender")
    private Long authorId;
    @Column(name = "recipient_of_the_message")
    private Long recipientId;

    @PersistenceConstructor
    protected SimpleMessageDto(){
    }

    public SimpleMessageDto(Long id, String message, Long authorId, Long recipientId) {
        this.id = id;
        this.message = message;
        this.authorId = authorId;
        this.recipientId = recipientId;
    }

    public Long getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }

    public Long getAuthor() {
        return authorId;
    }

    public Long getRecipient() {
        return recipientId;
    }

}
