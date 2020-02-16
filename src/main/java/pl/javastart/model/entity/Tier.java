package pl.javastart.model.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "tiers")
public class Tier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private pl.javastart.model.enums.Tier tier;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "tier_id")
    private List<TierImage> tiers;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "tier_id")
    private List<Price> prices;

    public Tier() {
    }

    public Tier(pl.javastart.model.enums.Tier tier) {
        this.tier = tier;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public pl.javastart.model.enums.Tier getTier() {
        return tier;
    }

    public void setTier(pl.javastart.model.enums.Tier tier) {
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