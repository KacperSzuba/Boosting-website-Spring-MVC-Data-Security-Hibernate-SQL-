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
    }

    void makeOrder(){
        orderBoostRepository2.save(this.orderBoost2);
    }

    double calculatePrice(){
        double sum = 0;
        League currentLeague = leagueRepository.findByTierAndDivisionAndPoints(orderBoost2.getCurrentLeague().getTier(), orderBoost2.getCurrentLeague().getDivision(), orderBoost2.getCurrentLeague().getPoints());
        League destinationLeague = leagueRepository.findByTierAndDivisionAndPointsEquals(orderBoost2.getDestinationLeague().getTier(), orderBoost2.getDestinationLeague().getDivision(), "0-20");
        List<Tier> tiers = Tier.valuesSinceTier2(currentLeague.getTier(),destinationLeague.getTier());
        for (Tier tier: tiers){
            if(tier.equals(currentLeague.getTier())){
                if(tiers.size()>1) {
                    for (int i = Integer.parseInt(currentLeague.getDivision()); i >=1; i--) {
                        if(tier.equals(currentLeague.getTier())&&Integer.parseInt(currentLeague.getDivision())==i){
                            System.out.println("PIERWSZA !!! "+tier + " : " + i);
                            sum += currentLeague.getPrice();
                        }
                        else {
                            System.out.println(tier + " : " + i);
                            sum += leagueRepository.findByTierAndDivisionAndPoints(tier,String.valueOf(i),"0-20").getPrice();
                        }
                    }
                }
                else {
                    for (int i = Integer.parseInt(currentLeague.getDivision()); i >Integer.parseInt(destinationLeague.getDivision()); i--) {
                        if(tier.equals(currentLeague.getTier())&&Integer.parseInt(currentLeague.getDivision())==i){
                            System.out.println("PIERWSZA !!! "+tier + " : " + i);
                            sum += currentLeague.getPrice();
                        }
                        else {
                            System.out.println(tier + " : " + i);
                            sum += leagueRepository.findByTierAndDivisionAndPoints(tier,String.valueOf(i),"0-20").getPrice();
                        }
                    }
                }
            }
            else if(tier.equals(destinationLeague.getTier())) {
                for (int i =4; i>Integer.parseInt(destinationLeague.getDivision());i--) {
                    System.out.println(tier + " : " + i);
                    sum += leagueRepository.findByTierAndDivisionAndPoints(tier,String.valueOf(i),"0-20").getPrice();
                }
            }
            else {
                for (int i =4; i>=1;i--){
                    System.out.println(tier+" : "+i);
                    sum += leagueRepository.findByTierAndDivisionAndPoints(tier,String.valueOf(i),"0-20").getPrice();
                }
            }
        }
        System.out.println(sum);
        return currentLeague.getPrice();
    }

}
