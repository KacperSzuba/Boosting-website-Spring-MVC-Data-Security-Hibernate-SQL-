package com.BoostingWebsite.employment;

import com.BoostingWebsite.order.division.Region;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@DynamicUpdate
@Entity
@Table(name = "booster_application")
public class BoosterApplication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String skype;

    private String discord;

    @NotEmpty
    private String email;

    @NotEmpty(message = "Is required")
    private String opgg;

    private Region region;

    private String leagueRank;

    private String AverageNumberOfHoursPlayedDaily;

    @NotNull(message = "Is required")
    private Integer Age;

    @NotEmpty(message = "Is required")
    private String country;

    @NotEmpty(message = "Is required")
    private String bestFormOfContact;

    @NotEmpty(message = "Is required")
    @Column(length = 2000)
    private String motivation;

    BoosterApplication() {
    }

    public BoosterApplication(String skype, String discord, String email, String opgg, Region region, String leagueRank, Integer age,
                              String country, String bestFormOfContact, String motivation) {
        this.skype = skype;
        this.discord = discord;
        this.email = email;
        this.opgg = opgg;
        this.region = region;
        this.leagueRank = leagueRank;
        Age = age;
        this.country = country;
        this.bestFormOfContact = bestFormOfContact;
        this.motivation = motivation;
    }

    public Long getId() {
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
        return AverageNumberOfHoursPlayedDaily;
    }

    public void setAverageNumberOfHoursPlayedDaily(String averageNumberOfHoursPlayedDaily) {
        AverageNumberOfHoursPlayedDaily = averageNumberOfHoursPlayedDaily;
    }

    public Integer getAge() {
        return Age;
    }

    public void setAge(Integer age) {
        Age = age;
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

    @Override
    public String toString() {
        return "BoosterApplication{" +
                "id=" + id +
                ", skype='" + skype + '\'' +
                ", discord='" + discord + '\'' +
                ", email='" + email + '\'' +
                ", opgg='" + opgg + '\'' +
                ", region=" + region +
                ", tier='" + leagueRank + '\'' +
                ", AverageNumberOfHoursPlayedDaily='" + AverageNumberOfHoursPlayedDaily + '\'' +
                ", Age=" + Age +
                ", country='" + country + '\'' +
                ", bestFormOfContact='" + bestFormOfContact + '\'' +
                ", motivation=" + motivation +
                '}';
    }
}
