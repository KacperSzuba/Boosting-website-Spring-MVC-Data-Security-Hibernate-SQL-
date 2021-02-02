package com.BoostingWebsite.api;

import com.BoostingWebsite.order.OrderBoostFacade;
import com.BoostingWebsite.order.dto.LeagueDto;
import com.BoostingWebsite.order.enumeration.Division;
import com.BoostingWebsite.order.enumeration.Region;
import com.BoostingWebsite.order.enumeration.Tier;
import com.BoostingWebsite.order.exception.OrderBoostNotFoundException;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class LeagueOfLegendsAPIFacade {
    private final OrderBoostFacade orderBoostFacade;

    public LeagueOfLegendsAPIFacade(OrderBoostFacade orderBoostFacade) {
        this.orderBoostFacade = orderBoostFacade;
    }

    public LeagueDto getCurrentLeague() throws OrderBoostNotFoundException, IOException {
        String username = orderBoostFacade.findActiveBoost().getLolUsername();
        Region region = orderBoostFacade.findActiveBoost().getRegion();

        LeagueOfLegendsAPIConnector leagueOfLegendsAPIConnector = new LeagueOfLegendsAPIConnector(username, region);

        Tier tier = Tier.valueOf(leagueOfLegendsAPIConnector.getActualSoloDuoTier());
        Division division = Division.getDivision(leagueOfLegendsAPIConnector.getActualSoloDuoDivision());
        int leaguePoints = Integer.parseInt(leagueOfLegendsAPIConnector.getActualSoloDuoLeaguePoints());

        return LeagueDto.builder()
                .withTier(tier)
                .withDivision(division)
                .withLeaguePoints(leaguePoints)
                .build();
    }
}
