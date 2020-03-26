package com.BoostingWebsite.order.division;

import java.util.ArrayList;
import java.util.List;

public enum Tier {
    IRON,
    BRONZE,
    SILVER,
    GOLD,
    PLATINUM,
    DIAMOND;

    public static int valueOf(Tier tier){
        Tier[] tiers = Tier.values();
        int result = 0;
        for (int i = 0; i <tiers.length ; i++) {
            if(tier.equals(tiers[i])){
                result = i;
            }
        }
        return result;
    }

    public static List<Tier> valuesSinceTier2(Tier currentTier,Tier destinationTier){
        List<Tier> tiers = new ArrayList<>();
        boolean isEquals = false;
        Tier[] tiers1 = Tier.values();
        for(int i=0;i<=Tier.valueOf(destinationTier);i++){
            if(currentTier.equals(tiers1[i])|| isEquals){
                isEquals = true;
                tiers.add(tiers1[i]);
            }
        }
        return tiers;
    }

}