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
    private User user;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="recipientOfTheMessage")
    private User user2;
    private LocalDateTime date;
    public Message(){ }

    public Message(String title, String message, User user, User user2) {
        this.title = title;
        this.message = message;
        this.user = user;
        this.user2 = user2;
        this.date = LocalDateTime.now();
    }

    public Message(Message copyMessage) {
        this.id = copyMessage.id;
        this.title = copyMessage.title;
        this.message = copyMessage.message;
        this.user = copyMessage.user;
        this.user2 = copyMessage.user2;
        this.date = LocalDateTime.now();
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser2() {
        return user2;
    }

    public void setUser2(User user2) {
        this.user2 = user2;
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
                ", user=" + user +
                ", user2=" + user2 +
                ", date=" + date +
                '}';
    }
}
