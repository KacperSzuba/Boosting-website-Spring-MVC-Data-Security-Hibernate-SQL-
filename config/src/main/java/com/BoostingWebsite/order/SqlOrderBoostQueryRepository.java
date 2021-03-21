package com.BoostingWebsite.order;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

interface SqlOrderBoostQueryRepository extends CrudRepository<OrderBoostSnapshot, Long> {
    @Query(value = "select orderboost from OrderBoostSnapshot orderboost where (orderboost.user.id =:id or orderboost.booster.id=:id) and orderboost.status = 'NEW'")
    Optional<OrderBoostSnapshot> findActiveBoost(@Param("id") Long id);

    boolean findByUser_IdOrBooster_IdAndStatus(@Param("userId") Long userId, @Param("boosterId") Long boosterId, @Param("status") EnumOrderStatus status);

    boolean existsByBooster_IdOrUser_IdAndStatus(Long boosterId, Long userId, EnumOrderStatus orderStatus);

    @Query(value = "select orderboost from OrderBoostSnapshot  orderboost where orderboost.status = 'NEW'")
    List<OrderBoostSnapshot> getFreeOrderBoosts();

    @Query(value = "select orderboost from OrderBoostSnapshot orderboost where orderboost.status = 'COMPLETED' and orderboost.booster.id =:id")
    List<OrderBoostSnapshot> findDoneOrderBoost(@Param("id") Long id);

    OrderBoostSnapshot save(OrderBoostSnapshot orderBoost);
}

