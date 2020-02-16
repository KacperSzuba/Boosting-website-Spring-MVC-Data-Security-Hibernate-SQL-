package pl.javastart.repository;

import org.springframework.data.repository.CrudRepository;
import pl.javastart.model.entity.Divisions;

public interface DivisionsRepository extends CrudRepository<Divisions,Long> {
}


