package com.BoostingWebsite.order;

import com.BoostingWebsite.account.SimpleUserDto;
import com.BoostingWebsite.account.UserQueryRepository;
import com.BoostingWebsite.order.dto.OrderBoostDto;
import com.BoostingWebsite.order.exception.OrderBoostNotFoundException;
import com.BoostingWebsite.utils.ApplicationSession;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderBoostFacade {
    private final ApplicationSession applicationSession;
    private final OrderBoostQueryRepository orderBoostQueryRepository;
    private final OrderExtrasRepository orderExtrasRepository;
    private final UserQueryRepository userQueryRepository;
    private final LeagueFactory leagueFactory;

    public OrderBoostFacade(final ApplicationSession applicationSession, final OrderBoostQueryRepository orderBoostQueryRepository,
                            final OrderExtrasRepository orderExtrasRepository, final UserQueryRepository userQueryRepository, LeagueFactory leagueFactory) {
        this.applicationSession = applicationSession;
        this.orderBoostQueryRepository = orderBoostQueryRepository;
        this.orderExtrasRepository = orderExtrasRepository;
        this.userQueryRepository = userQueryRepository;
        this.leagueFactory = leagueFactory;
    }

    boolean isLeagueIsValid(OrderBoost orderBoost) {
        int priorityOfCurrentLeague = orderBoost.getCurrentLeague().getTier().getPriority() + orderBoost.getCurrentLeague().getDivision().getPriority();
        int priorityOfDestinationLeague = orderBoost.getDestinationLeague().getTier().getPriority() + orderBoost.getDestinationLeague().getDivision().getPriority();

        return priorityOfDestinationLeague > priorityOfCurrentLeague;
    }

    public void makeOrder(OrderBoost orderBoost) {
        SimpleUserDto user = userQueryRepository.getById(applicationSession.getActualUser().getId()).get();

        orderBoost.setDate(LocalDateTime.now());
        orderBoost.setUser(user);
        orderBoost.setStatus(EnumOrderStatus.NEW);
        orderBoostQueryRepository.save(orderBoost);
    }

    public boolean whetherUserHasOrder(){
        return orderBoostQueryRepository.existsByBooster_IdOrUser_IdAndStatus(applicationSession.getActualUser().getId(), applicationSession.getActualUser().getId(), EnumOrderStatus.NEW);
    }

    public List<OrderExtras> getOrderExtras(){
        return (List<OrderExtras>) orderExtrasRepository.findAll();
    }

    public List<OrderBoostDto> getFreeOrderBoosts(){
        return orderBoostQueryRepository.getFreeOrderBoosts().stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public List<OrderBoostDto> getCompletedOrderBoosts(){
        return orderBoostQueryRepository.findDoneOrderBoost(applicationSession.getActualUser().getId()).stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public OrderBoostDto findActiveBoost() throws OrderBoostNotFoundException {
        return orderBoostQueryRepository
                .findActiveBoost(applicationSession.getActualUser().getId())
                .map(this::toDto)
                .orElseThrow(OrderBoostNotFoundException::new);
    }

    public boolean isActiveBoost(){
        return orderBoostQueryRepository.findByUser_IdOrBooster_IdAndStatus(applicationSession.getActualUser().getId(), applicationSession.getActualUser().getId(), EnumOrderStatus.NEW);
    }

    private OrderBoostDto toDto(OrderBoost orderBoost){
        return OrderBoostDto.builder()
                .withId(orderBoost.getId())
                .withNoteToBooster(orderBoost.getNoteToBooster())
                .withPaid(orderBoost.isPaid())
                .withPrice(orderBoost.getPrice())
                .withSummonerID(orderBoost.getSummonerID())
                .withLolUsername(orderBoost.getLolUsername())
                .withLolPassword(orderBoost.getLolPassword())
                .withRegion(orderBoost.getRegion())
                .withCurrentLeague(leagueFactory.toDto(orderBoost.getCurrentLeague()))
                .withDestinationLeague(leagueFactory.toDto(orderBoost.getDestinationLeague()))
                .withUser(orderBoost.getUser())
                .withBooster(orderBoost.getBooster())
                .withExtras(orderBoost.getExtras())
                .withStatus(orderBoost.getStatus())
                .withQueueType(orderBoost.getQueueType())
                .withMessages(orderBoost.getMessages())
                .withDate(orderBoost.getDate()).build();
    }
}