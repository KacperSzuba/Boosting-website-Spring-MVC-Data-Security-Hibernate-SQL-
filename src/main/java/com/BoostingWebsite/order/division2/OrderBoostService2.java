package com.BoostingWebsite.order.division2;

import com.BoostingWebsite.account.user.ActualUser;
import com.BoostingWebsite.order.division2.entity.League;
import com.BoostingWebsite.order.division2.repository.LeagueRepository;
import com.BoostingWebsite.order.division2.entity.OrderBoost2;
import com.BoostingWebsite.order.division2.repository.OrderBoostRepository2;
import org.springframework.stereotype.Service;

@Service
public class OrderBoostService2 {

    private OrderBoost2 orderBoost2;

    private ActualUser actualUser;
    private LeagueRepository leagueRepository;
    private OrderBoostRepository2 orderBoostRepository2;

    public OrderBoostService2(ActualUser actualUser, LeagueRepository leagueRepository, OrderBoostRepository2 orderBoostRepository2) {
        this.actualUser = actualUser;
        this.leagueRepository = leagueRepository;
        this.orderBoostRepository2 = orderBoostRepository2;
    }


    boolean isLeagueIsValid(OrderBoost2 orderBoost2){
        League currentLeague = leagueRepository.findByTierAndDivisionAndPoints(orderBoost2.getCurrentLeague().getTier(), orderBoost2.getCurrentLeague().getDivision(), orderBoost2.getCurrentLeague().getPoints());
        League destinationLeague = leagueRepository.findByTierAndDivisionAndPointsEquals(orderBoost2.getDestinationLeague().getTier(), orderBoost2.getDestinationLeague().getDivision(), "0-20");
        return currentLeague.getId() > destinationLeague.getId();
    }

    void setLeagues(OrderBoost2 orderBoost2){
        this.orderBoost2 = new OrderBoost2();
        this.orderBoost2.setCurrentLeague(orderBoost2.getCurrentLeague());
        this.orderBoost2.setDestinationLeague(orderBoost2.getDestinationLeague());
    }

    void setOrderInformation(OrderBoost2 orderBoost2){
        this.orderBoost2.setUser(actualUser.getActualUser());
        this.orderBoost2.setAccountDetails(orderBoost2.getAccountDetails());
        this.orderBoost2.setNoteToBooster(orderBoost2.getNoteToBooster());
    }

    void makeOrder(){
        orderBoostRepository2.save(this.orderBoost2);
    }

}
