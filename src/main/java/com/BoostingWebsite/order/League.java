package com.BoostingWebsite.order;

import com.BoostingWebsite.order.dto.LeagueDto;
import com.BoostingWebsite.order.enumeration.Division;
import com.BoostingWebsite.order.enumeration.Points;
import com.BoostingWebsite.order.enumeration.Tier;
import org.springframework.data.annotation.PersistenceConstructor;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Transient;

@Embeddable
public class League{
    @Enumerated(EnumType.STRING)
    private Tier tier;

    @Enumerated(EnumType.STRING)
    private Division division;

    @Enumerated(EnumType.STRING)
    private Points points;

    @Transient
    private int leaguePoints;

    @PersistenceConstructor
    public League() {
    }

    public League(Tier tier, Division division, int leaguePoints) {
        this.tier = tier;
        this.division = division;
        this.leaguePoints = leaguePoints;
    }

    public Tier getTier() {
        return tier;
    }

    void setTier(Tier tier) {
        this.tier = tier;
    }

    public Division getDivision() {
        return division;
    }

    void setDivision(Division division) {
        this.division = division;
    }

    public Points getPoints() {
        return points;
    }

    void setPoints(Points points) {
        this.points = points;
    }

    public int getLeaguePoints() {
        return leaguePoints;
    }

    void setLeaguePoints(int leaguePoints) {
        this.leaguePoints = leaguePoints;
    }

    LeagueDto toDto(){
        return LeagueDto.builder()
                .withTier(tier)
                .withDivision(division)
                .withLeaguePoints(leaguePoints)
                .build();
    }

    League from(LeagueDto leagueDto){
        return new League(leagueDto.getTier(), leagueDto.getDivision(), leagueDto.getLeaguePoints());
    }
}
