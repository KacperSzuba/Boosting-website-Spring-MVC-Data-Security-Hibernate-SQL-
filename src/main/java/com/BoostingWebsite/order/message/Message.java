package com.BoostingWebsite.order.message;

import com.BoostingWebsite.account.User;
import com.BoostingWebsite.order.message.dto.MessageDTO;
import org.springframework.data.annotation.PersistenceConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "messages")
class Message implements Comparable<Message> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String message;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "message_sender")
    private User author;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "recipient_of_the_message")
    private User recipient;

    private LocalDateTime date;

    @PersistenceConstructor
    protected Message() {
    }

    Message(String message, User author, User recipient) {
        this.message = message;
        this.author = author;
        this.recipient = recipient;
        this.date = LocalDateTime.now();
    }

    Long getId() {
        return id;
    }

    User getAuthor() {
        return author;
    }

    void setAuthor(User author) {
        this.author = author;
    }

    User getRecipient() {
        return recipient;
    }

    void setRecipient(User recipient) {
        this.recipient = recipient;
    }

    String getMessage() {
        return message;
    }

    void setMessage(String message) {
        this.message = message;
    }

    LocalDateTime getDate() {
        return date;
    }

    @Override
    public int compareTo(Message o) {
        return getDate().compareTo(o.getDate());
    }

    MessageDTO toDto(){
        return MessageDTO.builder()
                .withSenderName(author.getUsername())
                .withRecipientName(recipient.getUsername())
                .withContent(message)
                .build();
    }

    @Override
    public String toString() {
        return "Message{" +
                "id =" + id +
                ", sendMessage ='" + message + '\'' +
                ", date =" + date +
                '}';
    }

    @PrePersist
    void prePersist(){
        this.date = LocalDateTime.now();
    }
}
