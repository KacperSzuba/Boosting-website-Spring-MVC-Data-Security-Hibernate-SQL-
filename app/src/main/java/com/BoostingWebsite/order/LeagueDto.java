package com.BoostingWebsite.order;

public class LeagueDto {
    public static Builder builder(){
        return new Builder();
    }

    private final Tier tier;
    private final Division division;
    private final int leaguePoints;

    private LeagueDto(Builder builder) {
        tier = builder.tier;
        division = builder.division;
        leaguePoints = builder.leaguePoints;
    }

    public Tier getTier() {
        return tier;
    }

    public Division getDivision() {
        return division;
    }

    public int getLeaguePoints() {
        return leaguePoints;
    }

    public static class Builder{
        private Tier tier;
        private Division division;
        private int leaguePoints;

        private Builder(){}

        public LeagueDto build(){
            return new LeagueDto(this);
        }

        public Builder withTier(Tier tier) {
            this.tier = tier;
            return this;
        }

        public Builder withDivision(Division division) {
            this.division = division;
            return this;
        }

        public Builder withLeaguePoints(int leaguePoints) {
            this.leaguePoints = leaguePoints;
            return this;
        }
    }
}
