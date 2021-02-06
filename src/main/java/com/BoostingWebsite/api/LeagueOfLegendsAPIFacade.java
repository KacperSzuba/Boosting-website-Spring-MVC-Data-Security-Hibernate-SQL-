package com.BoostingWebsite.api;

import com.BoostingWebsite.order.dto.LeagueDto;
import com.BoostingWebsite.order.enumeration.Division;
import com.BoostingWebsite.order.enumeration.Tier;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class LeagueOfLegendsAPIFacade {
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
