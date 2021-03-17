package com.BoostingWebsite.order;

import com.BoostingWebsite.account.SimpleUserDto;
import com.BoostingWebsite.account.SimpleUserDtoSnapshot;
import com.BoostingWebsite.boosterApplication.Region;
import com.BoostingWebsite.order.message.SimpleMessageDto;
import org.hibernate.annotations.Type;
import org.springframework.data.annotation.PersistenceConstructor;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "orders_boost")
class OrderBoost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String noteToBooster;

    @Type(type = "org.hibernate.type.NumericBooleanType")
    private boolean paid;

    private BigDecimal price;

    private String summonerID;

    @NotEmpty(message = "Username cannot be empty")
    private String lolUsername;

    @NotEmpty(message = "Password cannot be empty")
    private String lolPassword;

    @Enumerated(EnumType.STRING)
    private Region region;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "tier", column = @Column(name = "currentTier")),
            @AttributeOverride(name = "division", column = @Column(name = "currentDivision")),
            @AttributeOverride(name = "points", column = @Column(name = "currentPoints"))}
    )
    private League currentLeague;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "tier", column = @Column(name = "destinationTier")),
            @AttributeOverride(name = "division", column = @Column(name = "destinationDivision")),
            @AttributeOverride(name = "points", column = @Column(name = "destinationPoints"))}
    )
    private League destinationLeague;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "users_id")
    private SimpleUserDtoSnapshot user;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "boosters_id")
    private SimpleUserDtoSnapshot booster;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "order_boost_extras",
            joinColumns = @JoinColumn(name = "order_boost_id"),
            inverseJoinColumns = @JoinColumn(name = "order_extras_id")
    )
    private Set<OrderExtras> extras;

    @Enumerated(EnumType.STRING)
    @NotNull
    private EnumOrderStatus status;

    @Enumerated(EnumType.STRING)
    @NotNull
    private QueueType queueType;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SimpleMessageDto> messages;

    private LocalDateTime date;

    @PersistenceConstructor
    protected OrderBoost() {
        status = EnumOrderStatus.NEW;
    }

    Long getId() {
        return id;
    }

    String getNoteToBooster() {
        return noteToBooster;
    }

    void setNoteToBooster(String noteToBooster) {
        this.noteToBooster = noteToBooster;
    }

    boolean isPaid() {
        return paid;
    }

    void setPaid(boolean whetherPaid) {
        this.paid = whetherPaid;
    }

    BigDecimal getPrice() {
        return price;
    }

    void setPrice(BigDecimal price) {
        this.price = price;
    }

    League getCurrentLeague() {
        return currentLeague;
    }

    void setCurrentLeague(League currentLeagueDto) {
        this.currentLeague = currentLeague;
    }

    League getDestinationLeague() {
        return destinationLeague;
    }

    void setDestinationLeague(League destinationLeague) {
        this.destinationLeague = destinationLeague;
    }

    SimpleUserDtoSnapshot getUser() {
        return user;
    }

    void setUser(SimpleUserDtoSnapshot user) {
        this.user = user;
    }

    SimpleUserDtoSnapshot getBooster() {
        return booster;
    }

    void setBooster(SimpleUserDtoSnapshot booster) {
        this.booster = booster;
    }

    List<SimpleMessageDto> getMessages() {
        return messages;
    }

    void setMessages(List<SimpleMessageDto> messages) {
        this.messages = messages;
    }

    LocalDateTime getDate() {
        return date;
    }

    void setDate(LocalDateTime date) {
        this.date = date;
    }

    EnumOrderStatus getStatus() {
        return status;
    }

    void setStatus(EnumOrderStatus status) {
        this.status = status;
    }

    QueueType getQueueType() {
        return queueType;
    }

    void setQueueType(QueueType queueType) {
        this.queueType = queueType;
    }

    Set<OrderExtras> getExtras() {
        return extras;
    }

    void setExtras(Set<OrderExtras> extras) {
        this.extras = extras;
    }

    String getSummonerID() {
        return summonerID;
    }

    void setSummonerID(String summonerID) {
        this.summonerID = summonerID;
    }

    String getLolUsername() {
        return lolUsername;
    }

    void setLolUsername(String lolUsername) {
        this.lolUsername = lolUsername;
    }

    public String getLolPassword() {
        return lolPassword;
    }

    void setLolPassword(String lolPassword) {
        this.lolPassword = lolPassword;
    }

    Region getRegion() {
        return region;
    }

    void setRegion(Region region) {
        this.region = region;
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
                ", user=" + user +
                ", booster=" + booster +
                ", date=" + date +
                '}';
    }
}
