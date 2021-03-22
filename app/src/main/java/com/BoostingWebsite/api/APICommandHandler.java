package com.BoostingWebsite.api;

import com.BoostingWebsite.order.LeagueDto;

import java.io.IOException;

public class APICommandHandler {
    private final LeagueOfLegendsAPIBusiness leagueOfLegendsAPIBusiness;

    public APICommandHandler(LeagueOfLegendsAPIBusiness leagueOfLegendsAPIBusiness) {
        this.leagueOfLegendsAPIBusiness = leagueOfLegendsAPIBusiness;
    }


    public LeagueDto getCurrentLeague() throws IOException {
        return leagueOfLegendsAPIBusiness.getCurrentLeague();
    }
}
