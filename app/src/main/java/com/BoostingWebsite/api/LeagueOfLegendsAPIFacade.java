package com.BoostingWebsite.api;

import com.BoostingWebsite.order.LeagueDto;
import com.BoostingWebsite.order.Division;
import com.BoostingWebsite.order.Tier;
import com.BoostingWebsite.utils.BaseFacade;

import java.io.IOException;

public class LeagueOfLegendsAPIFacade extends BaseFacade {
    private final LeagueOfLegendsAPIConnector leagueOfLegendsAPIConnector;

    public LeagueOfLegendsAPIFacade(final LeagueOfLegendsAPIConnector leagueOfLegendsAPIConnector) {
        this.leagueOfLegendsAPIConnector = leagueOfLegendsAPIConnector;
    }

    public LeagueDto getCurrentLeague() throws IOException {
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
