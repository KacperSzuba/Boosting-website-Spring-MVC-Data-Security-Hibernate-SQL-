package com.BoostingWebsite.order;

import java.math.BigDecimal;

class OrderBoostPriceCalculator {
    private double sum = 0;

    public static BigDecimal calculate(OrderBoost orderBoost){

        return BigDecimal.ZERO;
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
