package com.BoostingWebsite.order;

import com.BoostingWebsite.account.SimpleUserDto;
import com.BoostingWebsite.order.dto.OrderBoostDto;
import com.BoostingWebsite.order.exception.OrderBoostNotFoundException;
import com.BoostingWebsite.order.message.SimpleMessageDto;
import com.BoostingWebsite.utils.BaseBusiness;

import java.util.List;
import java.util.stream.Collectors;

class OrderBoostBusiness extends BaseBusiness {
    private final OrderBoostQueryRepository orderBoostQueryRepository;
    private final LeagueFactory leagueFactory;

    OrderBoostBusiness(final OrderBoostQueryRepository orderBoostQueryRepository, LeagueFactory leagueFactory) {
        this.orderBoostQueryRepository = orderBoostQueryRepository;
        this.leagueFactory = leagueFactory;
    }

    void makeOrder(OrderBoost orderBoost, SimpleUserDto userDto) {
        if (orderBoost.isLeagueIsValid()){
            orderBoost.makeOrder(userDto.getSnapshot());
            orderBoostQueryRepository.save(orderBoost);
        }

        throw new IllegalArgumentException("Your league is invalid!");
    }

    boolean whetherUserHasOrder(){
        return orderBoostQueryRepository.existsByBooster_IdOrUser_IdAndStatus(applicationSession.getContext().getUser().getId(), applicationSession.getContext().getUser().getId(), EnumOrderStatus.NEW);
    }

    List<OrderBoostDto> getFreeOrderBoosts(){
        return orderBoostQueryRepository.getFreeOrderBoosts().stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    List<OrderBoostDto> getCompletedOrderBoosts(){
        return orderBoostQueryRepository.findDoneOrderBoost(applicationSession.getContext().getUser().getId()).stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    OrderBoostDto findActiveBoost() throws OrderBoostNotFoundException {
        return orderBoostQueryRepository
                .findActiveBoost(applicationSession.getContext().getUser().getId())
                .map(this::toDto)
                .orElseThrow(() -> new OrderBoostNotFoundException("Order boost not found!"));
    }

    boolean isActiveBoost(){
        return orderBoostQueryRepository.findByUser_IdOrBooster_IdAndStatus(applicationSession.getContext().getUser().getId(), applicationSession.getContext().getUser().getId(), EnumOrderStatus.NEW);
    }

    private OrderBoostDto toDto(OrderBoost orderBoost){
        OrderBoostSnapshot snapshot = orderBoost.getSnapshot();
        return OrderBoostDto.builder()
                .withId(snapshot.getId())
                .withNoteToBooster(snapshot.getNoteToBooster())
                .withPaid(snapshot.isPaid())
                .withPrice(snapshot.getPrice())
                .withSummonerID(snapshot.getSummonerID())
                .withLolUsername(snapshot.getLolUsername())
                .withLolPassword(snapshot.getLolPassword())
                .withRegion(snapshot.getRegion())
                .withCurrentLeague(leagueFactory.toDto(snapshot.getCurrentLeague()))
                .withDestinationLeague(leagueFactory.toDto(snapshot.getDestinationLeague()))
                .withUser(snapshot.getUser())
                .withBooster(snapshot.getBooster())
                .withExtras(snapshot.getExtras().stream().map(OrderExtras::restore).collect(Collectors.toSet()))
                .withStatus(snapshot.getStatus())
                .withQueueType(snapshot.getQueueType())
                .withMessages(snapshot.getMessages().stream().map(SimpleMessageDto::restore).collect(Collectors.toList()))
                .withDate(snapshot.getDate()).build();
    }
}