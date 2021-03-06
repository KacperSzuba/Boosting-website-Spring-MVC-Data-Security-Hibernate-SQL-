package com.BoostingWebsite.order.dto;

import com.BoostingWebsite.account.SimpleUserDto;
import com.BoostingWebsite.order.LeagueDto;
import com.BoostingWebsite.order.OrderExtras;
import com.BoostingWebsite.order.EnumOrderStatus;
import com.BoostingWebsite.order.QueueType;
import com.BoostingWebsite.boosterApplication.Region;
import com.BoostingWebsite.order.message.SimpleMessageDto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

public class OrderBoostDto {
    public static Builder builder(){
        return new Builder();
    }

    private final Long id;
    private final String noteToBooster;
    private final boolean paid;
    private final BigDecimal price;
    private final String summonerID;
    private final String lolUsername;
    private final String lolPassword;
    private final Region region;
    private final LeagueDto currentLeagueDto;
    private final LeagueDto destinationLeagueDto;
    private final SimpleUserDto user;
    private final SimpleUserDto booster;
    private final Set<OrderExtras> extras;
    private final EnumOrderStatus status;
    private final QueueType queueType;
    private final List<SimpleMessageDto> messages;
    private final LocalDateTime date;

    private OrderBoostDto(Builder builder) {
        id = builder.id;
        noteToBooster = builder.noteToBooster;
        paid = builder.paid;
        price = builder.price;
        summonerID = builder.summonerID;
        lolUsername = builder.lolUsername;
        lolPassword = builder.lolPassword;
        region = builder.region;
        currentLeagueDto = builder.currentLeagueDto;
        destinationLeagueDto = builder.destinationLeagueDto;
        user = builder.user;
        booster = builder.booster;
        extras = builder.extras;
        status = builder.status;
        queueType = builder.queueType;
        messages = builder.messages;
        date = builder.date;
    }

    public Long getId() {
        return id;
    }

    public String getNoteToBooster() {
        return noteToBooster;
    }

    public boolean isPaid() {
        return paid;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getSummonerID() {
        return summonerID;
    }

    public String getLolUsername() {
        return lolUsername;
    }

    public String getLolPassword() {
        return lolPassword;
    }

    public Region getRegion() {
        return region;
    }

    public LeagueDto getCurrentLeague() {
        return currentLeagueDto;
    }

    public LeagueDto getDestinationLeague() {
        return destinationLeagueDto;
    }

    public SimpleUserDto getUser() {
        return user;
    }

    public SimpleUserDto getBooster() {
        return booster;
    }

    public Set<OrderExtras> getExtras() {
        return extras;
    }

    public EnumOrderStatus getStatus() {
        return status;
    }

    public QueueType getQueueType() {
        return queueType;
    }

    public List<SimpleMessageDto> getMessages() {
        return messages;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public static class Builder{
        private Long id;
        private String noteToBooster;
        private boolean paid;
        private BigDecimal price;
        private String summonerID;
        private String lolUsername;
        private String lolPassword;
        private Region region;
        private LeagueDto currentLeagueDto;
        private LeagueDto destinationLeagueDto;
        private SimpleUserDto user;
        private SimpleUserDto booster;
        private Set<OrderExtras> extras;
        private EnumOrderStatus status;
        private QueueType queueType;
        private List<SimpleMessageDto> messages;
        private LocalDateTime date;

        private Builder(){}

        public OrderBoostDto build(){
            return new OrderBoostDto(this);
        }

        public Builder withId(Long id) {
            this.id = id;
            return this;
        }

        public Builder withNoteToBooster(String noteToBooster) {
            this.noteToBooster = noteToBooster;
            return this;
        }

        public Builder withPaid(boolean paid) {
            this.paid = paid;
            return this;
        }

        public Builder withPrice(BigDecimal price) {
            this.price = price;
            return this;
        }

        public Builder withSummonerID(String summonerID) {
            this.summonerID = summonerID;
            return this;
        }

        public Builder withLolUsername(String lolUsername) {
            this.lolUsername = lolUsername;
            return this;
        }

        public Builder withLolPassword(String lolPassword) {
            this.lolPassword = lolPassword;
            return this;
        }

        public Builder withRegion(Region region) {
            this.region = region;
            return this;
        }

        public Builder withCurrentLeague(LeagueDto currentLeagueDto) {
            this.currentLeagueDto = currentLeagueDto;
            return this;
        }

        public Builder withDestinationLeague(LeagueDto destinationLeagueDto) {
            this.destinationLeagueDto = destinationLeagueDto;
            return this;
        }

        public Builder withUser(SimpleUserDto user) {
            this.user = user;
            return this;
        }

        public Builder withBooster(SimpleUserDto booster) {
            this.booster = booster;
            return this;
        }

        public Builder withExtras(Set<OrderExtras> extras) {
            this.extras = extras;
            return this;
        }

        public Builder withStatus(EnumOrderStatus status) {
            this.status = status;
            return this;
        }

        public Builder withQueueType(QueueType queueType) {
            this.queueType = queueType;
            return this;
        }

        public Builder withMessages(List<SimpleMessageDto> messages) {
            this.messages = messages;
            return this;
        }

        public Builder withDate(LocalDateTime date) {
            this.date = date;
            return this;
        }
    }
}
