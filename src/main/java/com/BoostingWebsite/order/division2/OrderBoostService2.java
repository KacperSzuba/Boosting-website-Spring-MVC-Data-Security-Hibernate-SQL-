package com.BoostingWebsite.order.division2;

import com.BoostingWebsite.account.user.ActualUser;
import com.BoostingWebsite.order.division.Tier;
import com.BoostingWebsite.order.division2.entity.League;
import com.BoostingWebsite.order.division2.repository.LeagueRepository;
import com.BoostingWebsite.order.division2.entity.OrderBoost2;
import com.BoostingWebsite.order.division2.repository.OrderBoostRepository2;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderBoostService2 {

    private double sum2 = 0;


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
        League currentLeague = leagueRepository.findByTierAndDivisionAndPoints(orderBoost2.getCurrentLeague().getTier(),orderBoost2.getCurrentLeague().getDivision(),orderBoost2.getCurrentLeague().getPoints());
        League destinationLeague = leagueRepository.findByTierAndDivisionAndPoints(orderBoost2.getDestinationLeague().getTier(),orderBoost2.getDestinationLeague().getDivision(),"0-20");
        this.orderBoost2.setCurrentLeague(currentLeague);
        this.orderBoost2.setDestinationLeague(destinationLeague);
    }

    void setOrderInformation(OrderBoost2 orderBoost2){
        this.orderBoost2.setUser(actualUser.getActualUser());
        this.orderBoost2.setAccountDetails(orderBoost2.getAccountDetails());
        this.orderBoost2.setNoteToBooster(orderBoost2.getNoteToBooster());
        this.orderBoost2.setPrice(calculatePrice());
        sum2 = 0;
    }

    void makeOrder(){
        orderBoostRepository2.save(this.orderBoost2);
    }


    private double calculatePrice(){
        List<Tier> tiers = Tier.tiers(currentLeague().getTier(),destinationLeague().getTier());
        for (Tier tier: tiers){
            if(compareCurrentTiers(tier)){
                if(tiers.size()>1) {
                    int currentDivision = Integer.parseInt(currentLeague().getDivision());
                    for (int i = currentDivision; i >=1; i--) {
                        if(compareCurrentTiers(tier) && currentDivision == i){
                            sum2 += currentLeague().getPrice();
                        }
                        else {
                            sum2 += priceForLeague(tier,i);
                        }
                    }
                }
                else {
                    for (int i = Integer.parseInt(currentLeague().getDivision()); i >Integer.parseInt(destinationLeague().getDivision()); i--) {
                        if(compareCurrentTiers(tier) && Integer.parseInt(currentLeague().getDivision())==i){
                            sum2 += currentLeague().getPrice();
                        }
                        else {
                            sum2 += priceForLeague(tier,i);
                        }
                    }
                }
            }
            else if(compareDestinationTiers(tier)) {
                for (int i =4; i>Integer.parseInt(destinationLeague().getDivision());i--) {
                    sum2 += priceForLeague(tier,i);
                }
            }
            else {
                for (int i =4; i>=1;i--){
                    sum2 += priceForLeague(tier,i);
                }
            }
        }
        return sum2;
    }



    private boolean compareDestinationTiers(Tier tier){
        return tier.equals(destinationLeague().getTier());
    }

    private boolean compareCurrentTiers(Tier tier){
        return tier.equals(currentLeague().getTier());
    }

    private League currentLeague(){
        return leagueRepository.findByTierAndDivisionAndPoints(orderBoost2.getCurrentLeague().getTier(), orderBoost2.getCurrentLeague().getDivision(), orderBoost2.getCurrentLeague().getPoints());
    }

    private League destinationLeague(){
        return leagueRepository.findByTierAndDivisionAndPointsEquals(orderBoost2.getDestinationLeague().getTier(), orderBoost2.getDestinationLeague().getDivision(), "0-20");
    }

    private double priceForLeague(Tier tier,int division){
        return leagueRepository.findByTierAndDivisionAndPoints(tier,String.valueOf(division),"0-20").getPrice();
    }
}