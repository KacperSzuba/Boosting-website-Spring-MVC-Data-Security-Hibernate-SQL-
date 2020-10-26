package com.BoostingWebsite.order.repository;

import com.BoostingWebsite.order.entity.League;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface LeagueRepository extends CrudRepository<League, Long> {
    @Query(value = "select league from League league group by league.tier order by league.id desc")
    List<League> findAllTiersOrderByIdDesc();

    @Query(value = "select league from League league group by league.division")
    List<League> findAllDivisions();

    @Query(value = "select league from League league group by league.points")
    List<League> findAllPoints();
}
