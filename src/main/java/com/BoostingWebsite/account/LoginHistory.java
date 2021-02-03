package com.BoostingWebsite.account;

import com.BoostingWebsite.account.User;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.PersistenceConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@DynamicUpdate
@Entity
@Table(name = "login_history")
class LoginHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime date;

    @ManyToOne
    private User user;

    @PersistenceConstructor
    public LoginHistory() {
    }

    LoginHistory(User user) {
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public User getUser() {
        return user;
    }

    @PrePersist
    public void prePersist(){
        date = LocalDateTime.now();
    }
}
