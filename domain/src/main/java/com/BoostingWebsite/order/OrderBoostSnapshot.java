package com.BoostingWebsite.order;

import com.BoostingWebsite.account.SimpleUserDtoSnapshot;
import com.BoostingWebsite.boosterApplication.Region;
import com.BoostingWebsite.order.message.SimpleMessageDtoSnapshot;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

class OrderBoostSnapshot {
    private Long id;
    private String noteToBooster;
    private boolean paid;
    private BigDecimal price;
    private String summonerID;
    private String lolUsername;
    private String lolPassword;
    private Region region;
    private League currentLeague;
    private League destinationLeague;
    private SimpleUserDtoSnapshot user;
    private SimpleUserDtoSnapshot booster;
    private Set<OrderExtrasSnapshot> extras;
    private EnumOrderStatus status;
    private QueueType queueType;
    private List<SimpleMessageDtoSnapshot> messages;
    private LocalDateTime date;

    OrderBoostSnapshot(
            Long id,
            String noteToBooster,
            boolean paid,
            BigDecimal price,
            String summonerID,
            String lolUsername,
            String lolPassword,
            Region region,
            League currentLeague,
            League destinationLeague,
            SimpleUserDtoSnapshot user,
            SimpleUserDtoSnapshot booster,
            Set<OrderExtrasSnapshot> extras,
            EnumOrderStatus status,
            QueueType queueType,
            List<SimpleMessageDtoSnapshot> messages,
            LocalDateTime date) {
        this.id = id;
        this.noteToBooster = noteToBooster;
        this.paid = paid;
        this.price = price;
        this.summonerID = summonerID;
        this.lolUsername = lolUsername;
        this.lolPassword = lolPassword;
        this.region = region;
        this.currentLeague = currentLeague;
        this.destinationLeague = destinationLeague;
        this.user = user;
        this.booster = booster;
        this.extras = extras;
        this.status = status;
        this.queueType = queueType;
        this.messages = messages;
        this.date = date;
    }

    Long getId() {
        return id;
    }

    String getNoteToBooster() {
        return noteToBooster;
    }

    boolean isPaid() {
        return paid;
    }

    BigDecimal getPrice() {
        return price;
    }

    String getSummonerID() {
        return summonerID;
    }

    String getLolUsername() {
        return lolUsername;
    }

    String getLolPassword() {
        return lolPassword;
    }

    Region getRegion() {
        return region;
    }

    League getCurrentLeague() {
        return currentLeague;
    }

    League getDestinationLeague() {
        return destinationLeague;
    }

    SimpleUserDtoSnapshot getUser() {
        return user;
    }

    SimpleUserDtoSnapshot getBooster() {
        return booster;
    }

    Set<OrderExtrasSnapshot> getExtras() {
        return extras;
    }

    EnumOrderStatus getStatus() {
        return status;
    }

    QueueType getQueueType() {
        return queueType;
    }

    List<SimpleMessageDtoSnapshot> getMessages() {
        return messages;
    }

    LocalDateTime getDate() {
        return date;
    }
}
