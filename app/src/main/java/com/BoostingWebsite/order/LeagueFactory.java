package com.BoostingWebsite.order;

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
