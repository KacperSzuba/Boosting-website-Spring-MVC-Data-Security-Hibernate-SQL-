package com.BoostingWebsite.order;

import com.BoostingWebsite.account.user.ApplicationSession;
import com.BoostingWebsite.order.entity.OrderBoost;
import com.BoostingWebsite.order.repository.LeagueRepository;
import com.BoostingWebsite.order.repository.OrderBoostRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class OrderBoostService {

    private double sum = 0;

    private ApplicationSession applicationSession;
    private LeagueRepository leagueRepository;
    private OrderBoostRepository orderBoostRepository;

    public OrderBoostService(ApplicationSession applicationSession, LeagueRepository leagueRepository, OrderBoostRepository orderBoostRepository) {
        this.applicationSession = applicationSession;
        this.leagueRepository = leagueRepository;
        this.orderBoostRepository = orderBoostRepository;
    }

    boolean isLeagueIsValid(OrderBoost orderBoost) {
        int priorityOfCurrentLeague = orderBoost.getCurrentLeague().getTier().getPriority() + orderBoost.getCurrentLeague().getDivision().getPriority();
        int priorityOfDestinationLeague = orderBoost.getDestinationLeague().getTier().getPriority() + orderBoost.getDestinationLeague().getDivision().getPriority();

        return priorityOfDestinationLeague > priorityOfCurrentLeague;
    }

    void makeOrder(OrderBoost orderBoost) {
        orderBoost.setDate(LocalDateTime.now());
        orderBoost.setUser(applicationSession.getActualUser());
        leagueRepository.save(orderBoost.getCurrentLeague());
        leagueRepository.save(orderBoost.getDestinationLeague());
        orderBoostRepository.save(orderBoost);
    }

    boolean whetherUserHasOrder(){
        return orderBoostRepository.existsByBoosterOrUserAndWhetherDone(applicationSession.getActualUser(), applicationSession.getActualUser(), false);
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