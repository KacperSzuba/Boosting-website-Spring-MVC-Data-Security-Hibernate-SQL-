package com.BoostingWebsite.order.entity;

import com.BoostingWebsite.order.Division;
import com.BoostingWebsite.order.Points;
import com.BoostingWebsite.order.Tier;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.PersistenceConstructor;

import javax.persistence.*;

@Entity
@DynamicUpdate
@DynamicInsert
@Table(name = "leagues")
public class League {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private Tier tier;

    @Enumerated(EnumType.STRING)
    private Division division;

    @Enumerated(EnumType.STRING)
    private Points points;

    private double price;

    private String currency;

    @Transient
    private int leaguePoints;

    @PersistenceConstructor
    public League() {
    }

    public League(Tier tier, Division division, Points points, double price) {
        this.tier = tier;
        this.division = division;
        this.points = points;
        this.price = price;
    }

    public League(Tier tier, Division division, int leaguePoints) {
        this.tier = tier;
        this.division = division;
        this.leaguePoints = leaguePoints;
    }

    public Long getId() {
        return id;
    }

    public Tier getTier() {
        return tier;
    }

    public void setTier(Tier tier) {
        this.tier = tier;
    }

    public Division getDivision() {
        return division;
    }

    public void setDivision(Division division) {
        this.division = division;
    }

    public Points getPoints() {
        return points;
    }

    public void setPoints(Points points) {
        this.points = points;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public int getLeaguePoints() {
        return leaguePoints;
    }

    public void setLeaguePoints(int leaguePoints) {
        this.leaguePoints = leaguePoints;
    }

    @Override
    public String toString() {
        return "League{" +
                "id=" + id +
                ", tier=" + tier +
                ", division='" + division + '\'' +
                ", points='" + points + '\'' +
                ", price=" + price +
                ", currency='" + currency + '\'' +
                '}';
    }
}
