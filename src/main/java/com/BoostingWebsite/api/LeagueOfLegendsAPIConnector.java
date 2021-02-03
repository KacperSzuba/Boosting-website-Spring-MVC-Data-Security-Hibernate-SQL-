package com.BoostingWebsite.api;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.BoostingWebsite.order.enumeration.Region;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

class LeagueOfLegendsAPIConnector {
    private final String region;
    private final String username;
    private final String summonerId;
    private final String apiKeyValue;
    private static final String HTTPS =  "https://";
    private static final String LOL_SUMMONER_BY_NAME = ".api.riotgames.com/lol/summoner/v4/summoners/by-name/";
    private static final String LOL_SUMMONER_ID = ".api.riotgames.com/lol/league/v4/entries/by-summoner/";
    private static final String API_KEY = "?api_key=";

    LeagueOfLegendsAPIConnector(String username, Region region) {
        this.username = username;
        this.region = region.getValue();
        summonerId = retrieveSummonerId();
        apiKeyValue = null;
    }

    LeagueOfLegendsAPIConnector(String apiKeyValue, String username, Region region) {
        this.username = username;
        this.region = region.getValue();
        summonerId = retrieveSummonerId();
        this.apiKeyValue = apiKeyValue;
    }

    private String retrieveSummonerId(){
        String summonerId;
        try {
            URL summoner = new URL(createURL(LOL_SUMMONER_BY_NAME, username));
            JsonObject jsonObject = (JsonObject) parser(summoner);
            summonerId = jsonObject.get("id").toString();

            return removeQuotes(summonerId);
        } catch (IOException e) {
            e.printStackTrace();

            throw new SummonerNotFoundException();
        }
    }

    private Map<String, JsonObject> retrieveSummonerLeague() throws IOException {
        URL summonerLeague = new URL(createURL(LOL_SUMMONER_ID, summonerId));
        JsonArray jsonArray = (JsonArray) parser(summonerLeague);

        Map<String, JsonObject> map = new HashMap<>();

        for (JsonElement jsonElement : jsonArray) {
            String key = removeQuotes(jsonElement.getAsJsonObject().get("queueType").toString());
            map.put(key, jsonElement.getAsJsonObject());
        }
        return map;
    }

    private Object parser(URL url) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
        JsonParser parser = new JsonParser();
        return parser.parse(reader.readLine());
    }

    private JsonObject getJsonObject(String queueType) throws IOException {
        return retrieveSummonerLeague().get(queueType);
    }

    String getActualSoloDuoTier() throws IOException {
        String tier = getJsonObject("RANKED_SOLO_5x5").get("tier").toString();
        return removeQuotes(tier);
    }

    String getActualSoloDuoDivision() throws IOException {
        String division = getJsonObject("RANKED_SOLO_5x5").get("rank").toString();
        return removeQuotes(division);
    }

    String getActualSoloDuoLeaguePoints() throws IOException {
        String leaguePoints = getJsonObject("RANKED_SOLO_5x5").get("leaguePoints").toString();
        return removeQuotes(leaguePoints);
    }

    String getActual5vs5Tier() throws IOException {
        String tier = getJsonObject("RANKED_FLEX_SR").get("tier").toString();
        return removeQuotes(tier);
    }

    String getActual5vs5Division() throws IOException {
        String division = getJsonObject("RANKED_FLEX_SR").get("rank").toString();
        return removeQuotes(division);
    }

    String getActual5vs5LeaguePoints() throws IOException {
        String leaguePoints = getJsonObject("RANKED_FLEX_SR").get("leaguePoints").toString();
        return removeQuotes(leaguePoints);
    }

    private static String removeQuotes(String text) {
        return text.replace("\"", "");
    }

    private String createURL(String apiLink, String user){
        StringBuilder builder = new StringBuilder();
        builder.append(HTTPS);
        builder.append(region);
        builder.append(apiLink);
        builder.append(user);
        builder.append(API_KEY);
        builder.append(apiKeyValue);

        return builder.toString();
    }
}
