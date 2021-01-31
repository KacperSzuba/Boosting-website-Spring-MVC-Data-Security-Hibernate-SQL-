package com.BoostingWebsite.order.entity;

import com.BoostingWebsite.order.Region;
import org.springframework.data.annotation.PersistenceConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "account_details")
public class AccountDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String summonerID;

    @NotEmpty(message = "Username cannot be empty")
    private String lolUsername;

    @NotEmpty(message = "Password cannot be empty")
    private String lolPassword;

    @Enumerated(EnumType.STRING)
    private Region region;

    @PersistenceConstructor
    public AccountDetails() {
    }

    public AccountDetails(String summonerID, String lolUsername, String lolPassword, Region region) {
        this.summonerID = summonerID;
        this.lolUsername = lolUsername;
        this.lolPassword = lolPassword;
        this.region = region;
    }

    public Long getId() {
        return id;
    }

    String getSummonerID() {
        return summonerID;
    }

    void setSummonerID(String summonerID) {
        this.summonerID = summonerID;
    }

    public String getLolUsername() {
        return lolUsername;
    }

    void setLolUsername(String lolUsername) {
        this.lolUsername = lolUsername;
    }

    public String getLolPassword() {
        return lolPassword;
    }

    void setLolPassword(String lolPassword) {
        this.lolPassword = lolPassword;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    @Override
    public String toString() {
        return "AccountDetails{" +
                "id=" + id +
                ", summonerID='" + summonerID + '\'' +
                ", lolUsername='" + lolUsername + '\'' +
                ", lolPassword='" + lolPassword + '\'' +
                ", region=" + region +
                '}';
    }
}
