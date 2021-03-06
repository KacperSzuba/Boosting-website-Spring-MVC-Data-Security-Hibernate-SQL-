package com.BoostingWebsite.api;

import com.BoostingWebsite.api.LeagueOfLegendsAPIConnector;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.MalformedURLException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

class LeagueOfLegendsAPIConnectorTest {
    private LeagueOfLegendsAPIConnector leagueConnector;

    @BeforeEach
    void initializeSummonerLeagueConnector() throws MalformedURLException {
        //given
       // leagueConnector = new LeagueOfLegendsAPIConnector("xVoleRx", Region.EUW);
    }

    @Test
    void tiersShouldBeCorrect() throws IOException {
        //then
       // assertThat(leagueConnector.getActualSoloDuoTier(), is(Tier.PLATINUM.toString()));
    }

    @Test
    void divisionsShouldBeCorrect() throws IOException {
        //then
       // assertThat(leagueConnector.getActualSoloDuoDivision(),is("2"));
    }

    @Test
    void leaguePointsShouldBeCorrect() throws IOException {
        //then
      //  assertThat(leagueConnector.getActualSoloDuoLeaguePoints(),is("59"));
    }
}