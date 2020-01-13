package pl.javastart.repository;

import org.springframework.data.repository.CrudRepository;
import pl.javastart.model.entity.Division;

import java.util.List;

public interface DivisionRepository extends CrudRepository<Division,Long> {
    List<Division> findAllByPointsAndDivision(String points, Integer division);
    List<Division> findAllByPoints(String points);
}
