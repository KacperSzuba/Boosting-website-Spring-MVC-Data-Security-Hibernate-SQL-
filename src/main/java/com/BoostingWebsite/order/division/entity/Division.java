package com.BoostingWebsite.order.division.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "divisions")
public class Division {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    public Division(){}
    private Integer division;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "division_id")
    private List<TierImage> tierImages;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "division_id")
    private List<Price> prices;

    public Long getId() {
        return id;
    }

    public Integer getDivision() {
        return division;
    }

    public void setDivision(Integer division) {
        this.division = division;
    }

    public List<TierImage> getTierImages() {
        return tierImages;
    }

    public void setTierImages(List<TierImage> tierImages) {
        this.tierImages = tierImages;
    }

    public List<Price> getPrices() {
        return prices;
    }

    public void setPrices(List<Price> prices) {
        this.prices = prices;
    }

}
