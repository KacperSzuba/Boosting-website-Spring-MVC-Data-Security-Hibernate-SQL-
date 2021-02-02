package com.BoostingWebsite.boosterApplication;

import com.BoostingWebsite.order.enumeration.Region;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@DynamicUpdate
@Entity
@Table(name = "booster_application")
class BoosterApplication {

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

    void setSkype(String skype) {
        this.skype = skype;
    }

    public String getDiscord() {
        return discord;
    }

    void setDiscord(String discord) {
        this.discord = discord;
    }

    public String getEmail() {
        return email;
    }

    void setEmail(String email) {
        this.email = email;
    }

    public String getOpgg() {
        return opgg;
    }

    void setOpgg(String opgg) {
        this.opgg = opgg;
    }

    public Region getRegion() {
        return region;
    }

    void setRegion(Region region) {
        this.region = region;
    }

    public String getLeagueRank() {
        return leagueRank;
    }

    void setLeagueRank(String leagueRank) {
        this.leagueRank = leagueRank;
    }

    public String getAverageNumberOfHoursPlayedDaily() {
        return AverageNumberOfHoursPlayedDaily;
    }

    void setAverageNumberOfHoursPlayedDaily(String averageNumberOfHoursPlayedDaily) {
        AverageNumberOfHoursPlayedDaily = averageNumberOfHoursPlayedDaily;
    }

    public Integer getAge() {
        return age;
    }

    void setAge(Integer age) {
        this.age = age;
    }

    public String getCountry() {
        return country;
    }

    void setCountry(String country) {
        this.country = country;
    }

    public String getBestFormOfContact() {
        return bestFormOfContact;
    }

    void setBestFormOfContact(String bestFormOfContact) {
        this.bestFormOfContact = bestFormOfContact;
    }

    public String getMotivation() {
        return motivation;
    }

    void setMotivation(String motivation) {
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
