package com.BoostingWebsite.order.division2.repository;

import com.BoostingWebsite.order.division.Tier;
import com.BoostingWebsite.order.division2.entity.League;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface LeagueRepository extends CrudRepository<League,Long> {

    League findByTierAndDivisionAndPoints(Tier tier, String division, String points);
    League findByTierAndDivisionAndPointsEquals(Tier tier, String division, String points);

    League findByTierAndDivision(Tier tier, String division);

    @Query(value = "select league from League league group by league.tier order by league.id desc")
    List<League> findAllTiersOrderByIdDesc();

    @Query(value = "select league from League league group by league.division")
    List<League> findAllDivisions();

    @Query(value = "select league from League league group by league.points")
    List<League> findAllPoints();

}
