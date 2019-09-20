package pl.javastart.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import pl.javastart.model.entity.OrderBoost;
import pl.javastart.model.entity.User;

import java.util.List;
import java.util.Optional;


public interface OrderBoostRepository extends CrudRepository<OrderBoost,Long> {
    OrderBoost findOrderBoostBylolUsername(String lolUsername);

    @Query(value = "SELECT orderboost FROM OrderBoost orderboost WHERE orderboost.booster = null")
    List<OrderBoost> findOrderBoostByBoosterEqualsNull();

    @Query(value = "SELECT orderboost FROM OrderBoost orderboost WHERE orderboost.booster=:u and orderboost.whetherDone=false")
    Optional<OrderBoost> checkIfTheBoosterHasNoOrders(@Param("u") User u);

    @Transactional
    @Modifying
    @Query(value = "UPDATE OrderBoost orderboost SET orderboost.booster =:user where orderboost.id=:id")
    void findFreeOrderBoosts(@Param("id") Long id, @Param("user")User user);
}
