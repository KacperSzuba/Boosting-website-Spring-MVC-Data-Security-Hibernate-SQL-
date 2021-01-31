package com.BoostingWebsite.order.preview;

import com.BoostingWebsite.account.user.User;
import com.BoostingWebsite.order.entity.OrderBoost;
import org.springframework.data.annotation.PersistenceConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "messages")
public class Message implements Comparable<Message> {

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

    private final LocalDateTime date;

    @ManyToOne(fetch = FetchType.EAGER)
    private OrderBoost orderBoost;

    @PersistenceConstructor
    public Message() {
        this.date = LocalDateTime.now();
    }

    public Message(String message, User author, User recipient) {
        this.message = message;
        this.author = author;
        this.recipient = recipient;
        this.date = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public User getAuthor() {
        return author;
    }

    void setAuthor(User author) {
        this.author = author;
    }

    public User getRecipient() {
        return recipient;
    }

    void setRecipient(User recipient) {
        this.recipient = recipient;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    LocalDateTime getDate() {
        return date;
    }

    public OrderBoost getOrderBoost() {
        return orderBoost;
    }

    public void setOrderBoost(OrderBoost orderBoost) {
        this.orderBoost = orderBoost;
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
}
