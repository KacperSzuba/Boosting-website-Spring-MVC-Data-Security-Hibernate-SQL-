package com.BoostingWebsite.order.division.entity;

import javax.persistence.*;

@Entity
@Table(name = "prices")
public class Price {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer price;

    @ManyToOne
    private Tier tier;

    @ManyToOne
    private Divisions division;

    @ManyToOne
    private DivisionPoints divisionPoints;

    public Price() {
    }

    public Price(Integer price, Tier tier, Divisions division, DivisionPoints divisionPoints) {
        this.price = price;
        this.tier = tier;
        this.division = division;
        this.divisionPoints = divisionPoints;
    }

    public Long getId() {
        return id;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Tier getTier() {
        return tier;
    }

    public void setTier(Tier tier) {
        this.tier = tier;
    }

    public Divisions getDivision() {
        return division;
    }

    public void setDivision(Divisions division) {
        this.division = division;
    }

    public DivisionPoints getDivisionPoints() {
        return divisionPoints;
    }

    public void setDivisionPoints(DivisionPoints divisionPoints) {
        this.divisionPoints = divisionPoints;
    }
}


