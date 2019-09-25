package pl.javastart.manage;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pl.javastart.model.entity.enums.Region;
import pl.javastart.model.entity.enums.Tier;

import java.io.IOException;
import java.net.MalformedURLException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;


class LeagueOfLegendsAPIConnectorTest {
    private LeagueOfLegendsAPIConnector leagueConnector;

    @BeforeEach
    void initializeSummonerLeagueConnector() throws MalformedURLException {
        //given
        leagueConnector = new LeagueOfLegendsAPIConnector("xVoleRx",Region.EUW);
    }

    @Test
    void tiersShouldBeCorrect() throws IOException {
        //then
        assertThat(leagueConnector.getActualSoloDuoTier(), is(Tier.DIAMOND.toString()));
        assertThat(leagueConnector.getActual5vs5Tier(), is(Tier.SILVER.toString()));
    }

    @Test
    void divisionsShouldBeCorrect() throws IOException {
        //then
        assertThat(leagueConnector.getActualSoloDuoDivision(),is("IV"));
        assertThat(leagueConnector.getActual5vs5Division(),is("III"));
    }

    @Test
    void leaguePointsShouldBeCorrect() throws IOException {
        //then
        assertThat(leagueConnector.getActualSoloDuoLeaguePoints(),is("33"));
        assertThat(leagueConnector.getActual5vs5LeaguePoints(),is("53"));
    }
}