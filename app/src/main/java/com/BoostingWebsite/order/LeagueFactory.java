package com.BoostingWebsite.order;

import org.springframework.stereotype.Service;

@Service
class LeagueFactory {
    LeagueDto toDto(League league){
        return LeagueDto.builder()
                .withTier(league.getTier())
                .withDivision(league.getDivision())
                .withLeaguePoints(league.getLeaguePoints())
                .build();
    }

    League from(LeagueDto leagueDto){
        return new League(leagueDto.getTier(), leagueDto.getDivision(), leagueDto.getLeaguePoints());
    }
}
