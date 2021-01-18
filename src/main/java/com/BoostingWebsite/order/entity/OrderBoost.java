package com.BoostingWebsite.order.entity;

import com.BoostingWebsite.account.user.User;
import com.BoostingWebsite.order.QueueType;
import com.BoostingWebsite.order.preview.Message;
import org.hibernate.annotations.Type;
import org.springframework.data.annotation.PersistenceConstructor;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "orders_boost")
public class OrderBoost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String noteToBooster;

    @Type(type = "org.hibernate.type.NumericBooleanType")
    private boolean paid;

    private double price;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "current_leagues_id")
    private League currentLeague;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "destination_leagues_id")
    private League destinationLeague;

    @Valid
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "accounts_details_id")
    private AccountDetails accountDetails;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "users_id")
    private User user;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "boosters_id")
    private User booster;

    @Enumerated(EnumType.STRING)
    private EnumOrderStatus status;

    @Enumerated(EnumType.STRING)
    @NotNull
    private QueueType queueType;

    @OneToMany(mappedBy = "orderBoost", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Message> messages;

    private LocalDateTime date;

    @PersistenceConstructor
    public OrderBoost() {
    }

    public OrderBoost(String noteToBooster, boolean whetherPaid, double price, League currentLeague, League destinationLeague,
                      AccountDetails accountDetails, User user, User booster, QueueType queueType) {
        this.noteToBooster = noteToBooster;
        this.paid = whetherPaid;
        this.price = price;
        this.currentLeague = currentLeague;
        this.destinationLeague = destinationLeague;
        this.accountDetails = accountDetails;
        this.user = user;
        this.booster = booster;
        this.queueType = queueType;
        this.date = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public String getNoteToBooster() {
        return noteToBooster;
    }

    public void setNoteToBooster(String noteToBooster) {
        this.noteToBooster = noteToBooster;
    }

    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean whetherPaid) {
        this.paid = whetherPaid;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public League getCurrentLeague() {
        return currentLeague;
    }

    public void setCurrentLeague(League currentLeague) {
        this.currentLeague = currentLeague;
    }

    public League getDestinationLeague() {
        return destinationLeague;
    }

    public void setDestinationLeague(League destinationLeague) {
        this.destinationLeague = destinationLeague;
    }

    public AccountDetails getAccountDetails() {
        return accountDetails;
    }

    public void setAccountDetails(AccountDetails accountDetails) {
        this.accountDetails = accountDetails;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getBooster() {
        return booster;
    }

    public void setBooster(User booster) {
        this.booster = booster;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public EnumOrderStatus getStatus() {
        return status;
    }

    public void setStatus(EnumOrderStatus status) {
        this.status = status;
    }

    public QueueType getQueueType() {
        return queueType;
    }

    void setQueueType(QueueType queueType) {
        this.queueType = queueType;
    }

    @Override
    public String toString() {
        return "OrderBoost2{" +
                "id=" + id +
                ", noteToBooster='" + noteToBooster + '\'' +
                ", whetherPaid=" + paid +
                ", price=" + price +
                ", currentLeague=" + currentLeague +
                ", destinationLeague=" + destinationLeague +
                ", accountDetails=" + accountDetails +
                ", user=" + user +
                ", booster=" + booster +
                ", date=" + date +
                '}';
    }
}
