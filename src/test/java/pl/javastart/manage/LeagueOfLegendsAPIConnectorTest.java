package pl.javastart.manage;

import com.google.gson.JsonObject;
import org.junit.jupiter.api.Test;
import pl.javastart.model.entity.enums.Region;

import java.io.IOException;
import java.net.MalformedURLException;

import static org.junit.jupiter.api.Assertions.*;

class LeagueOfLegendsAPIConnectorTest {

    @Test
    void shouldBeReturnJSonFile() throws IOException {
        LeagueOfLegendsAPIConnector leagueOfLegendsAPIConnector = new LeagueOfLegendsAPIConnector("xVoleRx", Region.EUW);
        System.out.println(leagueOfLegendsAPIConnector.connectWithLOLAPI());
        // String showName = jsonObject.get("name").toString();
    }

}