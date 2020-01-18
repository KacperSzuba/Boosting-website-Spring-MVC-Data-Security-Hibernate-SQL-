package pl.javastart.repository;

import org.springframework.data.repository.CrudRepository;
import pl.javastart.model.entity.Price;

public interface PriceRepository extends CrudRepository<Price,Long> {
}
