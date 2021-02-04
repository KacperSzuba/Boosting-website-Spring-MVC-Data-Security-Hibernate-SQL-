package com.BoostingWebsite.api;

import com.BoostingWebsite.order.OrderBoostFacade;
import com.BoostingWebsite.order.dto.LeagueDto;
import com.BoostingWebsite.order.enumeration.Division;
import com.BoostingWebsite.order.enumeration.Region;
import com.BoostingWebsite.order.enumeration.Tier;
import com.BoostingWebsite.order.exception.OrderBoostNotFoundException;
import com.BoostingWebsite.utils.ConstFacade;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class LeagueOfLegendsAPIFacade {
    private final OrderBoostFacade orderBoostFacade;
    private final ConstFacade constFacade;

    public LeagueOfLegendsAPIFacade(final OrderBoostFacade orderBoostFacade, final ConstFacade constFacade) {
        this.orderBoostFacade = orderBoostFacade;
        this.constFacade = constFacade;
    }

    public LeagueDto getCurrentLeague() throws OrderBoostNotFoundException, IOException {
        String username = orderBoostFacade.findActiveBoost().getLolUsername();
        Region region = orderBoostFacade.findActiveBoost().getRegion();

        LeagueOfLegendsAPIConnector leagueOfLegendsAPIConnector = new LeagueOfLegendsAPIConnector(constFacade.getLeagueOfLegendsApiKey(), username, region);

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
