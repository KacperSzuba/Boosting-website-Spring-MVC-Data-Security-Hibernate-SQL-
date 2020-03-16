package com.BoostingWebsite.order.division.entity;

import javax.persistence.*;

@Entity
@Table(name = "tier_images")
public class TierImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "img_source")
    private String imgSource;

    @ManyToOne
    private Tier tier;

    @ManyToOne
    private Divisions division;

    public TierImage() {
    }

    public TierImage(String imgSource) {
        this.imgSource = imgSource;
    }

    public Long getId() {
        return id;
    }

    public String getImgSource() {
        return imgSource;
    }

    public void setImgSource(String imgSource) {
        this.imgSource = imgSource;
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

    @Override
    public String toString() {
        return "TierImage{" +
                "id=" + id +
                ", imgSource='" + imgSource + '\'' +
                ", tier=" + tier +
                ", division=" + division +
                '}';
    }
}
