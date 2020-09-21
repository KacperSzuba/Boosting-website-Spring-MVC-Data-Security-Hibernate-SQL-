package com.BoostingWebsite;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.BoostingWebsite.order.Region;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class LeagueOfLegendsAPIConnector {
    private Region region;
    private String username;
    private final String apiKey = "RGAPI-63a08ccb-7bf1-4072-9d91-7a7a722c515c";

    public LeagueOfLegendsAPIConnector(String username, Region region) {
        this.username = username;
        this.region = region;
    }

    private Map<String,JsonObject> retrieveSummonerLeague() throws IOException {
        URL summonerLeague = new URL("https://" + region() + ".api.riotgames.com/lol/league/v4/entries/by-summoner/" + retrieveSummonerId() + "?api_key=" + apiKey);
        JsonArray jsonArray = (JsonArray) parser(summonerLeague);;
        Map<String,JsonObject> map = new HashMap<>();

        for (JsonElement jsonElement : jsonArray) {
            String key = removeQuotes(jsonElement.getAsJsonObject().get("queueType").toString());
            map.put(key,jsonElement.getAsJsonObject());
        }
        return map;
    }

    private String retrieveSummonerId() throws IOException {
        URL summoner = new URL("https://" + region() + ".api.riotgames.com/lol/summoner/v4/summoners/by-name/" + username + "?api_key=" + apiKey);
        JsonObject jsonObject = (JsonObject) parser(summoner);
        String summonerId = jsonObject.get("id").toString();
        return removeQuotes(summonerId);
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
        String tier = getJsonObject("RANKED_SOLO_5x5").get("tier").toString();
        return removeQuotes(tier);
    }

    public String getActualSoloDuoDivision() throws IOException {
        String division = getJsonObject("RANKED_SOLO_5x5").get("rank").toString();
        String str = removeQuotes(division);
        return mapOfDivisions().get(str);
    }

    public String getActualSoloDuoLeaguePoints() throws IOException {
        String leaguePoints = getJsonObject("RANKED_SOLO_5x5").get("leaguePoints").toString();
        return removeQuotes(leaguePoints);
    }

    public String getActual5vs5Tier() throws IOException {
        String tier = getJsonObject("RANKED_FLEX_SR").get("tier").toString();
        return removeQuotes(tier);
    }

    public String getActual5vs5Division() throws IOException {
        String division = getJsonObject("RANKED_FLEX_SR").get("rank").toString();
        String str = removeQuotes(division);
        return  mapOfDivisions().get(str);
    }

    public String getActual5vs5LeaguePoints() throws IOException {
        String leaguePoints = getJsonObject("RANKED_FLEX_SR").get("leaguePoints").toString();
        return removeQuotes(leaguePoints);
    }

    private String removeQuotes(String text){
        return text.replace("\"", "");
    }

    private Map<Region,String> mapOfRegions(){
        Map<Region ,String> mapOfRegions = new TreeMap<>();
        mapOfRegions.put(Region.EUW, "euw1");
        mapOfRegions.put(Region.EUNE, "eun1");
        mapOfRegions.put(Region.TR, "tr1");
        return mapOfRegions;
    }

    private Map<String,String> mapOfDivisions(){
        Map<String,String> mapOfDivisions = new TreeMap<>();
        mapOfDivisions.put("I", "1");
        mapOfDivisions.put("II", "2");
        mapOfDivisions.put("III", "3");
        mapOfDivisions.put("IV", "4");
        return mapOfDivisions;
    }

    private String region(){
        return mapOfRegions().get(region);
    }
}
