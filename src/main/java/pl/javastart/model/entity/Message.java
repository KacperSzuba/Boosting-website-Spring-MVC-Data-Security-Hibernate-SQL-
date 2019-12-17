package pl.javastart.model.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Message implements Comparable<Message>{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String message;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="messageSender")
    private User messageSender;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="recipientOfTheMessage")
    private User recipientOfTheMessage;
    private LocalDateTime date;
    public Message(){ }

    public Message(String title, String message, User messageSender, User recipientOfTheMessage) {
        this.title = title;
        this.message = message;
        this.messageSender = messageSender;
        this.recipientOfTheMessage = recipientOfTheMessage;
        this.date = LocalDateTime.now();
    }

    public Message(Message copyMessage) {
        this.id = copyMessage.id;
        this.title = copyMessage.title;
        this.message = copyMessage.message;
        this.messageSender = copyMessage.messageSender;
        this.recipientOfTheMessage = copyMessage.recipientOfTheMessage;
        this.date = LocalDateTime.now();
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getMessageSender() {
        return messageSender;
    }

    public void setMessageSender(User messageSender) {
        this.messageSender = messageSender;
    }

    public User getRecipientOfTheMessage() {
        return recipientOfTheMessage;
    }

    public void setRecipientOfTheMessage(User recipientOfTheMessage) {
        this.recipientOfTheMessage = recipientOfTheMessage;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getDate() {
        return date;
    }

    @Override
    public int compareTo(Message o) {
        return getDate().compareTo(o.getDate());
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", message='" + message + '\'' +
                ", user=" + messageSender +
                ", user2=" + recipientOfTheMessage +
                ", date=" + date +
                '}';
    }
}
