package pl.javastart.repository;

import org.springframework.data.repository.CrudRepository;
import pl.javastart.model.entity.OrderBoost;


public interface OrderRepository extends CrudRepository<OrderBoost,Long> {
    OrderBoost findOrderBoostByOrderName(String orderName);
}
