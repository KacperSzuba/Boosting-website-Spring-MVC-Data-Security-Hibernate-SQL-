package com.BoostingWebsite.order.message;

import com.BoostingWebsite.account.SimpleUserDto;
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
    private SimpleUserDto author;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "recipient_of_the_message")
    private SimpleUserDto recipient;

    private LocalDateTime date;

    @PersistenceConstructor
    protected Message() {
    }

    Message(String message, SimpleUserDto author, SimpleUserDto recipient) {
        this.message = message;
        this.author = author;
        this.recipient = recipient;
        this.date = LocalDateTime.now();
    }

    Long getId() {
        return id;
    }

    SimpleUserDto getAuthor() {
        return author;
    }

    void setAuthor(SimpleUserDto author) {
        this.author = author;
    }

    SimpleUserDto getRecipient() {
        return recipient;
    }

    void setRecipient(SimpleUserDto recipient) {
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
