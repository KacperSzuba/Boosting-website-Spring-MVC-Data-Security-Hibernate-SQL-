package com.BoostingWebsite.order;

import com.BoostingWebsite.account.SimpleUserDto;
import com.BoostingWebsite.account.UserBusiness;
import com.BoostingWebsite.order.dto.OrderBoostDto;
import com.BoostingWebsite.order.exception.OrderBoostNotFoundException;
import com.BoostingWebsite.order.message.SimpleMessageDto;
import com.BoostingWebsite.utils.BaseFacade;

import java.util.List;
import java.util.stream.Collectors;

public class OrderBoostBusiness extends BaseFacade {
    private final UserBusiness userBusiness;
    private final OrderBoostQueryRepository orderBoostQueryRepository;
    private final LeagueFactory leagueFactory;

    public OrderBoostBusiness(
            final UserBusiness userBusiness,
            final OrderBoostQueryRepository orderBoostQueryRepository,
            LeagueFactory leagueFactory) {
        this.userBusiness = userBusiness;
        this.orderBoostQueryRepository = orderBoostQueryRepository;
        this.leagueFactory = leagueFactory;
    }

    public void makeOrder(OrderBoost orderBoost) {
        if (orderBoost.isLeagueIsValid()){
            SimpleUserDto user = userBusiness.findSimpleUserDtoById(applicationSession.getContext().getUser().getId());
            orderBoost.makeOrder(user.getSnapshot());
            orderBoostQueryRepository.save(orderBoost);
        }

        throw new IllegalArgumentException("Your league is invalid!");
    }

    public boolean whetherUserHasOrder(){
        return orderBoostQueryRepository.existsByBooster_IdOrUser_IdAndStatus(applicationSession.getContext().getUser().getId(), applicationSession.getContext().getUser().getId(), EnumOrderStatus.NEW);
    }

    public List<OrderBoostDto> getFreeOrderBoosts(){
        return orderBoostQueryRepository.getFreeOrderBoosts().stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public List<OrderBoostDto> getCompletedOrderBoosts(){
        return orderBoostQueryRepository.findDoneOrderBoost(applicationSession.getContext().getUser().getId()).stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public OrderBoostDto findActiveBoost() throws OrderBoostNotFoundException {
        return orderBoostQueryRepository
                .findActiveBoost(applicationSession.getContext().getUser().getId())
                .map(this::toDto)
                .orElseThrow(OrderBoostNotFoundException::new);
    }

    public boolean isActiveBoost(){
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