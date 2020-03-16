package com.BoostingWebsite.order;

import org.hibernate.annotations.Type;
import com.BoostingWebsite.account.user.User;
import com.BoostingWebsite.order.division.Region;
import com.BoostingWebsite.order.division.Tier;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "order_boost")
public class OrderBoost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    @Column(name = "current_tier")
    private Tier currentTier;
    @Column(name = "current_division")
    private Integer currentDivision;
    @Enumerated(EnumType.STRING)
    @Column(name = "destination_tier")
    private Tier destinationTier;
    @Column(name = "destination_division")
    private Integer destinationDivision;
    @Column(name = "summoner_id")
    private String summonerID;
    @Column(name = "lol_username")
    private String lolUsername;
    @Column(name = "lol_password")
    private String lolPassword;
    @Enumerated(EnumType.STRING)
    private Region region;
    @Column(name = "note_to_boosters")
    private String noteToBoosters;
    private LocalDateTime date;
    @Type(type = "org.hibernate.type.NumericBooleanType")
    @Column(name = "whether_paid")
    private boolean whetherPaid;
    @Type(type = "org.hibernate.type.NumericBooleanType")
    @Column(name = "whether_done")
    private boolean whetherDone;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="user_id")
    private User user;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="booster_id")
    private User booster;

    OrderBoost() {}

     OrderBoost(Tier currentTier, Integer currentDivision, Tier destinationTier,
                      Integer destinationDivision, String summonerID, String lolUsername,
                      String lolPassword, Region region, String noteToBoosters, LocalDateTime date,
                      boolean whetherPaid, boolean whetherDone, User user,User booster) {
        this.currentTier = currentTier;
        this.currentDivision = currentDivision;
        this.destinationTier = destinationTier;
        this.destinationDivision = destinationDivision;
        this.summonerID = summonerID;
        this.lolUsername = lolUsername;
        this.lolPassword = lolPassword;
        this.region = region;
        this.noteToBoosters = noteToBoosters;
        this.date = date;
        this.whetherPaid = whetherPaid;
        this.whetherDone = whetherDone;
        this.user = user;
        this.booster = booster;
    }

    public Long getId() {
        return id;
    }

    public Tier getCurrentTier() {
        return currentTier;
    }

    void setCurrentTier(Tier currentTier) {
        this.currentTier = currentTier;
    }

    public Integer getCurrentDivision() {
        return currentDivision;
    }

    void setCurrentDivision(Integer currentDivision) {
        this.currentDivision = currentDivision;
    }

    public Tier getDestinationTier() {
        return destinationTier;
    }

    void setDestinationTier(Tier destinationTier) {
        this.destinationTier = destinationTier;
    }

    public Integer getDestinationDivision() {
        return destinationDivision;
    }

    void setDestinationDivision(Integer destinationDivision) {
        this.destinationDivision = destinationDivision;
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

    public String getNoteToBoosters() {
        return noteToBoosters;
    }

    public void setNoteToBoosters(String noteToBoosters) {
        this.noteToBoosters = noteToBoosters;
    }

    public LocalDateTime getDate() {
        return date;
    }

    void setDate(LocalDateTime date) {
        this.date = date;
    }

    public boolean isWhetherPaid() {
        return whetherPaid;
    }

    public boolean isWhetherDone() {
        return whetherDone;
    }

    public void setWhetherDone(boolean whetherDone) {
        this.whetherDone = whetherDone;
    }

    void setWhetherPaid(boolean whetherPaid) {
        this.whetherPaid = whetherPaid;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getBooster() {
        return booster;
    }

    public void setBooster(User booster) {
        this.booster = booster;
    }
}
