package com.BoostingWebsite.order;

import com.BoostingWebsite.account.User;
import com.BoostingWebsite.account.UserFacade;
import com.BoostingWebsite.order.dto.OrderBoostDto;
import com.BoostingWebsite.order.enumeration.EnumOrderStatus;
import com.BoostingWebsite.order.exception.OrderBoostNotFoundException;
import com.BoostingWebsite.utils.ApplicationSession;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderBoostFacade {
    private final ApplicationSession applicationSession;
    private final OrderBoostRepository orderBoostRepository;
    private final OrderExtrasRepository orderExtrasRepository;
    private final UserFacade userFacade;

    public OrderBoostFacade(ApplicationSession applicationSession, OrderBoostRepository orderBoostRepository, OrderExtrasRepository orderExtrasRepository, UserFacade userFacade) {
        this.applicationSession = applicationSession;
        this.orderBoostRepository = orderBoostRepository;
        this.orderExtrasRepository = orderExtrasRepository;
        this.userFacade = userFacade;
    }

    boolean isLeagueIsValid(OrderBoost orderBoost) {
        int priorityOfCurrentLeague = orderBoost.getCurrentLeague().getTier().getPriority() + orderBoost.getCurrentLeague().getDivision().getPriority();
        int priorityOfDestinationLeague = orderBoost.getDestinationLeague().getTier().getPriority() + orderBoost.getDestinationLeague().getDivision().getPriority();

        return priorityOfDestinationLeague > priorityOfCurrentLeague;
    }

    public void makeOrder(OrderBoost orderBoost) {
        User user = userFacade.findById(applicationSession.getActualUser().getId());

        orderBoost.setDate(LocalDateTime.now());
        orderBoost.setUser(user);
        orderBoost.setStatus(EnumOrderStatus.NEW);
        orderBoostRepository.save(orderBoost);
    }

    public boolean whetherUserHasOrder(){
        return orderBoostRepository.existsByBooster_IdOrUser_IdAndStatus(applicationSession.getActualUser().getId(), applicationSession.getActualUser().getId(), EnumOrderStatus.NEW);
    }

    public List<OrderExtras> getOrderExtras(){
        return (List<OrderExtras>) orderExtrasRepository.findAll();
    }

    public List<OrderBoostDto> getFreeOrderBoosts(){
        return orderBoostRepository.getFreeOrderBoosts().stream()
                .map(OrderBoost::toDto)
                .collect(Collectors.toList());
    }

    public List<OrderBoostDto> getCompletedOrderBoosts(){
        return orderBoostRepository.findDoneOrderBoost(applicationSession.getActualUser().getId()).stream()
                .map(OrderBoost::toDto)
                .collect(Collectors.toList());
    }

    public OrderBoostDto findActiveBoost() throws OrderBoostNotFoundException {
        return orderBoostRepository
                .findActiveBoost(applicationSession.getActualUser().getId())
                .map(OrderBoost::toDto)
                .orElseThrow(OrderBoostNotFoundException::new);
    }

    public boolean isActiveBoost(){
        return orderBoostRepository.findByUser_IdOrBooster_IdAndStatus(applicationSession.getActualUser().getId(), applicationSession.getActualUser().getId(), EnumOrderStatus.NEW);
    }
}