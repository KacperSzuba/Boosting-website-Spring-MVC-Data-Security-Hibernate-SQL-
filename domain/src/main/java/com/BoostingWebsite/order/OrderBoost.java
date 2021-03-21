package com.BoostingWebsite.order;

import com.BoostingWebsite.account.SimpleUserDto;
import com.BoostingWebsite.account.SimpleUserDtoSnapshot;
import com.BoostingWebsite.boosterApplication.Region;
import com.BoostingWebsite.order.message.SimpleMessageDto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

class OrderBoost {
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
    private Set<OrderExtras> extras;
    private EnumOrderStatus status;
    private QueueType queueType;
    private List<SimpleMessageDto> messages;
    private LocalDateTime date;

    OrderBoost() { }

    OrderBoost(
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
            Set<OrderExtras> extras,
            EnumOrderStatus status,
            QueueType queueType,
            List<SimpleMessageDto> messages,
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

    boolean isLeagueIsValid(){
        int priorityOfCurrentLeague = currentLeague.getTier().getPriority() + currentLeague.getDivision().getPriority();
        int priorityOfDestinationLeague = destinationLeague.getTier().getPriority() + destinationLeague.getDivision().getPriority();

        return priorityOfDestinationLeague > priorityOfCurrentLeague;
    }

    void makeOrder(SimpleUserDtoSnapshot user) {
        this.user = user;
        this.status = EnumOrderStatus.NEW;
        this.date = LocalDateTime.now();
    }

    static OrderBoost restore(OrderBoostSnapshot snapshot){
        return new OrderBoost(
                snapshot.getId(),
                snapshot.getNoteToBooster(),
                snapshot.isPaid(),
                snapshot.getPrice(),
                snapshot.getSummonerID(),
                snapshot.getLolUsername(),
                snapshot.getLolPassword(),
                snapshot.getRegion(),
                snapshot.getCurrentLeague(),
                snapshot.getDestinationLeague(),
                snapshot.getUser(),
                snapshot.getBooster(),
                snapshot.getExtras().stream().map(OrderExtras::restore).collect(Collectors.toSet()),
                snapshot.getStatus(),
                snapshot.getQueueType(),
                snapshot.getMessages().stream().map(SimpleMessageDto::restore).collect(Collectors.toList()),
                snapshot.getDate()
        );
    }

    OrderBoostSnapshot getSnapshot(){
        return new OrderBoostSnapshot(
                id,
                noteToBooster,
                paid,
                price,
                summonerID,
                lolUsername,
                lolPassword,
                region,
                currentLeague,
                destinationLeague,
                user,
                booster,
                extras.stream().map(OrderExtras::getSnapshot).collect(Collectors.toSet()),
                status,
                queueType,
                messages.stream().map(SimpleMessageDto::getSnapshot).collect(Collectors.toList()),
                date
        );
    }
}
