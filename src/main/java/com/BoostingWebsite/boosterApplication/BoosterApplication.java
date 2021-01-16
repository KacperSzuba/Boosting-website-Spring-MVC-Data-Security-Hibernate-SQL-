package com.BoostingWebsite.boosterApplication;

import com.BoostingWebsite.order.Region;
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

    @NotEmpty(message = "E-mail cannot be empty")
    private String email;

    @NotEmpty(message = "Opgg link cannot be empty")
    private String opgg;

    private Region region;

    private String leagueRank;

    private String AverageNumberOfHoursPlayedDaily;

    @NotNull(message = "Age cannot be empty")
    private Integer age;

    @NotEmpty(message = "Country cannot be empty")
    private String country;

    @NotEmpty(message = "Best form of contact cannot be empty")
    private String bestFormOfContact;

    @NotEmpty(message = "Your motivation cannot be empty")
    @Column(length = 2000)
    private String motivation;

    public BoosterApplication() {
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
                ", Age=" + age +
                ", country='" + country + '\'' +
                ", bestFormOfContact='" + bestFormOfContact + '\'' +
                ", motivation=" + motivation +
                '}';
    }
}
