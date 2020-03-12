package com.BoostingWebsite.message;

import com.BoostingWebsite.account.user.User;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "messages")
public class Message implements Comparable<Message>{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String message;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="message_sender")
    private User author;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="recipient_of_the_message")
    private User recipient;
    private LocalDateTime date;
    public Message(){
        this.date = LocalDateTime.now();
    }

    public Message(String message, User author, User recipient) {
        this.message = message;
        this.author = author;
        this.recipient = recipient;
        this.date = LocalDateTime.now();
    }

    public Message(Message copyMessage) {
        this.id = copyMessage.id;
        this.message = copyMessage.message;
        this.author = copyMessage.author;
        this.recipient = copyMessage.recipient;
        this.date = LocalDateTime.now();
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    Long getAuthorId(){
        return author.getId();
    }

    public User getRecipient() {
        return recipient;
    }

    public void setRecipient(User recipient) {
        this.recipient = recipient;
    }

    Long getRecipientId(){
        return recipient.getId();
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

    @Override
    public int compareTo(Message o) {
        return getDate().compareTo(o.getDate());
    }

    @Override
    public String toString() {
        return "Message{" +
                "id =" + id +
                ", sendMessage ='" + message + '\'' +
                ", author id =" + getAuthorId() +
                ", recipient id =" + getRecipientId() +
                ", date =" + date +
                '}';
    }
}
