package com.BoostingWebsite.order.division2;

import com.BoostingWebsite.account.user.ActualUser;
import com.BoostingWebsite.order.division2.entity.OrderBoost2;
import com.BoostingWebsite.order.division2.repository.LeagueRepository;
import com.BoostingWebsite.order.division2.repository.OrderBoostRepository2;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class OrderBoostService2 {

    private double sum = 0;

    private ActualUser actualUser;
    private LeagueRepository leagueRepository;
    private OrderBoostRepository2 orderBoostRepository2;

    public OrderBoostService2(ActualUser actualUser, LeagueRepository leagueRepository, OrderBoostRepository2 orderBoostRepository2) {
        this.actualUser = actualUser;
        this.leagueRepository = leagueRepository;
        this.orderBoostRepository2 = orderBoostRepository2;
    }

    boolean isLeagueIsValid(OrderBoost2 orderBoost2){
        int priorityOfCurrentLeague = orderBoost2.getCurrentLeague().getTier().getPriority() + orderBoost2.getCurrentLeague().getDivision().getPriority();
        int priorityOfDestinationLeague = orderBoost2.getDestinationLeague().getTier().getPriority() + orderBoost2.getDestinationLeague().getDivision().getPriority();

        return priorityOfDestinationLeague > priorityOfCurrentLeague;
    }

    void makeOrder(OrderBoost2 orderBoost2){
        orderBoost2.setDate(LocalDateTime.now());
        orderBoost2.setUser(actualUser.getActualUser());
        leagueRepository.save(orderBoost2.getCurrentLeague());
        leagueRepository.save(orderBoost2.getDestinationLeague());
        orderBoostRepository2.save(orderBoost2);
    }
/*
    private double calculatePrice(){
        List<Tier> tiers = Tier.tiers(currentLeague().getTier(), destinationLeague().getTier());
        for (Tier tier: tiers){
            if(compareCurrentTiers(tier)){
                calculatePriceFromCurrentTier(tier, tiers.size());
            }
            else if(compareDestinationTiers(tier)) {
                calculatePriceFromDestinationTier(tier);
            }
            else {
                calculatePriceFromWholeTier(tier);
            }
        }
        return sum;
    }

    private void calculatePriceFromCurrentTier(Tier tier, int tierSize){
        if(tierSize>1) {
            int currentDivision = Integer.parseInt(currentLeague().getDivision());
            for (int i = currentDivision; i >= 1; i--) {
                if(compareCurrentTiers(tier) && currentDivision == i){
                    sum += currentLeague().getPrice();
                }
                else {
                    sum += priceForLeague(tier, i);
                }
            }
        }
        else {
            for (int i = Integer.parseInt(currentLeague().getDivision()); i >Integer.parseInt(destinationLeague().getDivision()); i--) {
                if(compareCurrentTiers(tier) && Integer.parseInt(currentLeague().getDivision())==i){
                    sum += currentLeague().getPrice();
                }
                else {
                    sum += priceForLeague(tier, i);
                }
            }
        }
    }

    private void calculatePriceFromDestinationTier(Tier tier){
        for (int i =4; i > Integer.parseInt(destinationLeague().getDivision()); i--) {
            sum += priceForLeague(tier, i);
        }
    }

    private void calculatePriceFromWholeTier(Tier tier){
        for (int i =4; i>= 1; i--){
            sum += priceForLeague(tier, i);
        }
    }

    private double priceForLeague(Tier tier,int division){
        return leagueRepository.findByTierAndDivisionAndPoints(tier, String.valueOf(division),"0-20").getPrice();
    }

 */
}