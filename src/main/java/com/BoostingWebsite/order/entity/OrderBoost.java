package com.BoostingWebsite.order.entity;

import com.BoostingWebsite.account.user.User;
import com.BoostingWebsite.message.Message;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "orders_boost")
public class OrderBoost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "notes_to_booster")
    private String noteToBooster;

    @Type(type = "org.hibernate.type.NumericBooleanType")
    @Column(name = "whether_paid")
    private boolean whetherPaid;

    @Type(type = "org.hibernate.type.NumericBooleanType")
    @Column(name = "whether_done")
    private boolean whetherDone;

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

    @OneToMany(
            mappedBy = "orderBoost",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Message> messages;

    private LocalDateTime date;

    public OrderBoost() {
    }

    public OrderBoost(OrderBoost orderBoost) {
        this.id = orderBoost.id;
        this.noteToBooster = orderBoost.noteToBooster;
        this.whetherPaid = orderBoost.whetherPaid;
        this.whetherDone = orderBoost.whetherDone;
        this.price = orderBoost.price;
        this.currentLeague = orderBoost.currentLeague;
        this.destinationLeague = orderBoost.destinationLeague;
        this.accountDetails = orderBoost.accountDetails;
        this.user = orderBoost.user;
        this.booster = orderBoost.booster;
        this.date = LocalDateTime.now();
    }

    public OrderBoost(String noteToBooster, boolean whetherPaid, boolean whetherDone, double price, League currentLeague, League destinationLeague,
                      AccountDetails accountDetails, User user, User booster) {
        this.noteToBooster = noteToBooster;
        this.whetherPaid = whetherPaid;
        this.whetherDone = whetherDone;
        this.price = price;
        this.currentLeague = currentLeague;
        this.destinationLeague = destinationLeague;
        this.accountDetails = accountDetails;
        this.user = user;
        this.booster = booster;
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

    public boolean isWhetherPaid() {
        return whetherPaid;
    }

    public void setWhetherPaid(boolean whetherPaid) {
        this.whetherPaid = whetherPaid;
    }

    public boolean isWhetherDone() {
        return whetherDone;
    }

    public void setWhetherDone(boolean whetherDone) {
        this.whetherDone = whetherDone;
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

    @Override
    public String toString() {
        return "OrderBoost2{" +
                "id=" + id +
                ", noteToBooster='" + noteToBooster + '\'' +
                ", whetherPaid=" + whetherPaid +
                ", whetherDone=" + whetherDone +
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
