package pl.javastart.repository;

import org.springframework.data.repository.CrudRepository;
import pl.javastart.model.entity.OrderBoost;


public interface OrderBoostRepository extends CrudRepository<OrderBoost,Long> {
    OrderBoost findOrderBoostBylolUsername(String lolUsername);
}
