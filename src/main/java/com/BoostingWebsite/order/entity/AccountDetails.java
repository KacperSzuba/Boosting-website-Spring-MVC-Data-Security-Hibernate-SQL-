package com.BoostingWebsite.order.entity;

import com.BoostingWebsite.order.Region;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "account_details")
public class AccountDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "summoner_id")
    private String summonerID;

    @NotEmpty(message = "Username cannot be empty")
    @Column(name = "lol_username")
    private String lolUsername;

    @NotEmpty(message = "Password cannot be empty")
    @Column(name = "lol_password")
    private String lolPassword;

    @Enumerated(EnumType.STRING)
    private Region region;

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

    public String getSummonerID() {
        return summonerID;
    }

    public void setSummonerID(String summonerID) {
        this.summonerID = summonerID;
    }

    public String getLolUsername() {
        return lolUsername;
    }

    public void setLolUsername(String lolUsername) {
        this.lolUsername = lolUsername;
    }

    public String getLolPassword() {
        return lolPassword;
    }

    public void setLolPassword(String lolPassword) {
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
