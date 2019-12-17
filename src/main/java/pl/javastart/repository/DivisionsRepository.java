package pl.javastart.repository;

import org.springframework.data.repository.CrudRepository;
import pl.javastart.model.entity.Divisions;

import java.util.List;

public interface DivisionsRepository extends CrudRepository<Divisions,Long> {
    List<Divisions> findAllByPointsAndDivision(String points,Integer division);
    List<Divisions> findAllByPoints(String points);
}
