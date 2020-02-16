package pl.javastart.repository;

import org.springframework.data.repository.CrudRepository;
import pl.javastart.model.entity.Tier;

public interface TierRepository extends CrudRepository<Tier,Long> {
}


