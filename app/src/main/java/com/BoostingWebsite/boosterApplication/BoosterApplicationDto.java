package com.BoostingWebsite.boosterApplication;

public class BoosterApplicationDto {
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

    Long getId() {
        return id;
    }

    public String getSkype() {
        return skype;
    }

    public void setSkype(String skype) {
        this.skype = skype;
    }

    public String getDiscord() {
        return discord;
    }

    public void setDiscord(String discord) {
        this.discord = discord;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getOpgg() {
        return opgg;
    }

    public void setOpgg(String opgg) {
        this.opgg = opgg;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public String getLeagueRank() {
        return leagueRank;
    }

    public void setLeagueRank(String leagueRank) {
        this.leagueRank = leagueRank;
    }

    public String getAverageNumberOfHoursPlayedDaily() {
        return averageNumberOfHoursPlayedDaily;
    }

    public void setAverageNumberOfHoursPlayedDaily(String averageNumberOfHoursPlayedDaily) {
        this.averageNumberOfHoursPlayedDaily = averageNumberOfHoursPlayedDaily;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getBestFormOfContact() {
        return bestFormOfContact;
    }

    public void setBestFormOfContact(String bestFormOfContact) {
        this.bestFormOfContact = bestFormOfContact;
    }

    public String getMotivation() {
        return motivation;
    }

    public void setMotivation(String motivation) {
        this.motivation = motivation;
    }
}
