package com.BoostingWebsite.order.division2.entity;

import org.hibernate.annotations.Immutable;

import javax.persistence.*;

@Entity
@Immutable
public class TierImage2 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "img_source")
    private String imageSource;

    public TierImage2() {
    }

    public Long getId() {
        return id;
    }

    public String getImageSource() {
        return imageSource;
    }

}
