package com.BoostingWebsite.boosterApplication;

class BoosterApplicationSnapshot {
    private Long id;
    private String skype;
    private String discord;
    private String email;
    private String opgg;
    private Region region;
    private String leagueRank;
    private String averageNumberOfHoursPlayedDaily;
    private Integer age;
    private String country;
    private String bestFormOfContact;
    private String motivation;

    protected BoosterApplicationSnapshot(){}

    BoosterApplicationSnapshot(
            Long id,
            String skype,
            String discord,
            String email,
            String opgg,
            Region region,
            String leagueRank,
            String averageNumberOfHoursPlayedDaily,
            Integer age,
            String country,
            String bestFormOfContact,
            String motivation) {
        this.id = id;
        this.skype = skype;
        this.discord = discord;
        this.email = email;
        this.opgg = opgg;
        this.region = region;
        this.leagueRank = leagueRank;
        this.averageNumberOfHoursPlayedDaily = averageNumberOfHoursPlayedDaily;
        this.age = age;
        this.country = country;
        this.bestFormOfContact = bestFormOfContact;
        this.motivation = motivation;
    }

    Long getId() {
        return id;
    }

    String getSkype() {
        return skype;
    }

    String getDiscord() {
        return discord;
    }

    String getEmail() {
        return email;
    }

    String getOpgg() {
        return opgg;
    }

    Region getRegion() {
        return region;
    }

    String getLeagueRank() {
        return leagueRank;
    }

    String getAverageNumberOfHoursPlayedDaily() {
        return averageNumberOfHoursPlayedDaily;
    }

    Integer getAge() {
        return age;
    }

    String getCountry() {
        return country;
    }

    String getBestFormOfContact() {
        return bestFormOfContact;
    }

    String getMotivation() {
        return motivation;
    }
}
