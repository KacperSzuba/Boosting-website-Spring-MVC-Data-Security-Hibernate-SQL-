package com.BoostingWebsite.order;

class League{
    private Tier tier;
    private Division division;
    private Points points;
    private int leaguePoints;

    League(Tier tier, Division division, Points points) {
        this.tier = tier;
        this.division = division;
        this.points = points;
    }

    League(Tier tier, Division division, int leaguePoints) {
        this.tier = tier;
        this.division = division;
        this.leaguePoints = leaguePoints;
    }

    Tier getTier() {
        return tier;
    }

    Division getDivision() {
        return division;
    }

    Points getPoints() {
        return points;
    }

    int getLeaguePoints() {
        return leaguePoints;
    }
}
