package com.BoostingWebsite.order.division.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "divisions2")
public class Divisions {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer division;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "division_id")
    private List<TierImage> tiers;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "division_id")
    private List<Price> prices;

    public Divisions() {
    }

    public Divisions(Integer division) {
        this.division = division;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getDivision() {
        return division;
    }

    public void setDivision(Integer division) {
        this.division = division;
    }

    public List<TierImage> getTiers() {
        return tiers;
    }

    public void setTiers(List<TierImage> tiers) {
        this.tiers = tiers;
    }

    public List<Price> getPrices() {
        return prices;
    }

    public void setPrices(List<Price> prices) {
        this.prices = prices;
    }
}