package pl.javastart.repository;

import org.springframework.data.repository.CrudRepository;
import pl.javastart.model.entity.DivisionPoints;

public interface DivisionPointsRepository extends CrudRepository<DivisionPoints,Long> {
}
