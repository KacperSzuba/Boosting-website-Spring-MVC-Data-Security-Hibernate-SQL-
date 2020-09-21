package com.BoostingWebsite.order.division2.entity;

import com.BoostingWebsite.order.division2.Division;
import com.BoostingWebsite.order.division2.Points;
import com.BoostingWebsite.order.division2.Tier;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

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

    @Column(name = "divisions")
    @Enumerated(EnumType.STRING)
    private Division division;

    @Enumerated(EnumType.STRING)
    private Points points;

    @Column(name = "prices")
    private double price;

    private String currency;

    public League() {
    }

    public League(Tier tier, Division division, Points points, double price) {
        this.tier = tier;
        this.division = division;
        this.points = points;
        this.price = price;
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
