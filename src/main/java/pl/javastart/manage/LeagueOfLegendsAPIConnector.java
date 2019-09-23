package pl.javastart.manage;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import pl.javastart.model.entity.enums.Region;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class LeagueOfLegendsAPIConnector {
    private Region region;
    private String username;
    private String tier;
    private final String apiKey = "RGAPI-67e3f3cb-2f13-4118-a777-62cfd1bf83bb";

    LeagueOfLegendsAPIConnector(String username, Region region) throws MalformedURLException {
        this.username = username;
        this.region = region;
    }

    private Map<String,JsonObject> retrieveSummonerLeague() throws IOException {
        URL summonerLeague = new URL("https://"+region()+".api.riotgames.com/lol/league/v4/entries/by-summoner/"+retrieveSummoner()+"?api_key="+apiKey);
        JsonArray jsonArray = (JsonArray) parser(summonerLeague);;
        Map<String,JsonObject> map = new HashMap<>();
        for (JsonElement jsonElement : jsonArray) {
            String key = jsonElement.getAsJsonObject().get("queueType").toString();
            map.put(key,jsonElement.getAsJsonObject());
        }
        return map;
    }

    private String retrieveSummoner() throws IOException {
        URL summoner = new URL("https://"+region()+".api.riotgames.com/lol/summoner/v4/summoners/by-name/"+username+"?api_key="+apiKey);
        JsonObject jsonObject = (JsonObject) parser(summoner);
        String str = jsonObject.get("id").toString();
        return removeQuotes(str);
    }

    private Object parser(URL url) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
        JsonParser parser = new JsonParser();
        return parser.parse(reader.readLine());
    }
    private JsonObject getJsonObject(String queueType) throws IOException {
        return retrieveSummonerLeague().get(queueType);
    }

    public String getActualSoloDuoTier() throws IOException {
        String tier = getJsonObject("\"RANKED_SOLO_5x5\"").get("tier").toString();
        return removeQuotes(tier);
    }

    public String getActualSoloDuoDivision() throws IOException {
        String division = getJsonObject("\"RANKED_SOLO_5x5\"").get("rank").toString();
        return removeQuotes(division);
    }

    public String getActualSoloDuoLeaguePoints() throws IOException {
        String leaguePoints = getJsonObject("\"RANKED_SOLO_5x5\"").get("leaguePoints").toString();
        return removeQuotes(leaguePoints);
    }

    public String getActual5vs5Tier() throws IOException {
        String tier = getJsonObject("\"RANKED_FLEX_SR\"").get("tier").toString();
        return removeQuotes(tier);
    }

    public String getActual5vs5Division() throws IOException {
        String division = getJsonObject("\"RANKED_FLEX_SR\"").get("rank").toString();
        return removeQuotes(division);
    }

    public String getActual5vs5LeaguePoints() throws IOException {
        String leaguePoints = getJsonObject("\"RANKED_FLEX_SR\"").get("leaguePoints").toString();
        return removeQuotes(leaguePoints);
    }

    private String removeQuotes(String text){
        return text.replace("\"", "");
    }

    private Map<Region,String> listOfRegions(){
        Map<Region ,String> listOfRegions2 = new TreeMap<>();
        listOfRegions2.put(Region.EUW,"euw1");
        listOfRegions2.put(Region.EUNE,"eun1");
        listOfRegions2.put(Region.TR,"tr1");
        return listOfRegions2;
    }

    private String region(){
        return listOfRegions().get(region);
    }
}
