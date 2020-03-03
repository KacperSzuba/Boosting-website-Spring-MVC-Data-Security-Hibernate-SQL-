package com.BoostingWebsite.order.division.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "tiers")
public class Tier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private com.BoostingWebsite.order.division.Tier tier;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "tier_id")
    private List<TierImage> tiers;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "tier_id")
    private List<Price> prices;

    public Tier() {
    }

    public Tier(com.BoostingWebsite.order.division.Tier tier) {
        this.tier = tier;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public com.BoostingWebsite.order.division.Tier getTier() {
        return tier;
    }

    public void setTier(com.BoostingWebsite.order.division.Tier tier) {
        this.tier = tier;
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