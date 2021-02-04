package com.BoostingWebsite.order;

import com.BoostingWebsite.order.enumeration.EnumOrderStatus;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

interface SqlOrderBoostQueryRepository extends OrderBoostQueryRepository, CrudRepository<OrderBoost, Long> {
    @Query(value = "select orderboost from OrderBoost orderboost where (orderboost.user.id =:id or orderboost.booster.id=:id) and orderboost.status = 'NEW'")
    Optional<OrderBoost> findActiveBoost(@Param("id") Long id);

    boolean findByUser_IdOrBooster_IdAndStatus(@Param("userId") Long userId, @Param("boosterId") Long boosterId, @Param("status") EnumOrderStatus status);

    boolean existsByBooster_IdOrUser_IdAndStatus(Long boosterId, Long userId, EnumOrderStatus orderStatus);

    @Query(value = "select orderboost from OrderBoost  orderboost where orderboost.status = 'NEW'")
    List<OrderBoost> getFreeOrderBoosts();

    @Query(value = "select orderboost from OrderBoost orderboost where orderboost.status = 'COMPLETED' and orderboost.booster.id =:id")
    List<OrderBoost> findDoneOrderBoost(@Param("id") Long id);
}
