package pl.javastart.manage;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import pl.javastart.model.entity.enums.Region;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;
import java.util.TreeMap;

public class LeagueOfLegendsAPIConnector {

    private URL url;
    private BufferedReader reader;
    private JsonParser parser;
    private Object object;
    private JsonObject jsonObject;
    private Map<Region,String> map = new TreeMap<>();
    public LeagueOfLegendsAPIConnector(String username ,Region region) throws MalformedURLException {
        url = new URL("https://"+listOfRegions().get(region)+".api.riotgames.com/lol/summoner/v4/summoners/by-name/"+username+"?api_key=RGAPI-303dd9c3-95f5-485e-8a2a-034f3205b5a5");
    }

    public JsonObject connectWithLOLAPI() throws IOException {
        reader = new BufferedReader(new InputStreamReader(url.openStream()));
        parser = new JsonParser();
        object = parser.parse(reader.readLine());
        jsonObject = (JsonObject) object;
        return jsonObject;
    }

    private Map<Region,String> listOfRegions(){
        Map<Region ,String> listOfRegions2 = new TreeMap<>();
        listOfRegions2.put(Region.EUW,"euw1");
        listOfRegions2.put(Region.EUNE,"eun1");
        listOfRegions2.put(Region.TR,"tr1");
        return listOfRegions2;
    }
}