package com.BoostingWebsite.api;

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

public class LeagueOfLegendsAPIConnector {
    private final String region;
    private final String username;
    private final String summonerId;
    private final String apiKey = "RGAPI-2fd9dfa6-9de8-4f28-bee8-f70fb955ea38";

    public LeagueOfLegendsAPIConnector(String username, Region region) {
        this.username = username;
        this.region = region.getValue();
        summonerId = retrieveSummonerId();
    }

    private String retrieveSummonerId(){
        String summonerId;
        try {
            URL summoner = new URL("https://" + region + ".api.riotgames.com/lol/summoner/v4/summoners/by-name/" + username + "?api_key=" + apiKey);
            JsonObject jsonObject = (JsonObject) parser(summoner);
            summonerId = jsonObject.get("id").toString();

            return removeQuotes(summonerId);
        } catch (IOException e) {
            e.printStackTrace();

            throw new SummonerNotFoundException();
        }
    }

    private Map<String, JsonObject> retrieveSummonerLeague() throws IOException {
        URL summonerLeague = new URL("https://" + region + ".api.riotgames.com/lol/league/v4/entries/by-summoner/" + summonerId + "?api_key=" + apiKey);
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

    public String getActualSoloDuoTier() throws IOException {
        String tier = getJsonObject("RANKED_SOLO_5x5").get("tier").toString();
        return removeQuotes(tier);
    }

    public String getActualSoloDuoDivision() throws IOException {
        String division = getJsonObject("RANKED_SOLO_5x5").get("rank").toString();
        return removeQuotes(division);
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
        return removeQuotes(division);
    }

    public String getActual5vs5LeaguePoints() throws IOException {
        String leaguePoints = getJsonObject("RANKED_FLEX_SR").get("leaguePoints").toString();
        return removeQuotes(leaguePoints);
    }

    private static String removeQuotes(String text) {
        return text.replace("\"", "");
    }
}