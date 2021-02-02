package com.BoostingWebsite.order;

import com.BoostingWebsite.account.User;
import com.BoostingWebsite.order.enumeration.EnumOrderStatus;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
interface OrderBoostRepository extends CrudRepository<OrderBoost, Long> {
    @Transactional
    @Query(value = "select orderboost from OrderBoost orderboost where (orderboost.user.id =:id or orderboost.booster.id=:id) and orderboost.status = 'NEW'")
    Optional<OrderBoost> findActiveBoost(@Param("id") Long id);

    boolean existsByBooster_IdOrUser_IdAndStatus(Long boosterId, Long userId, EnumOrderStatus orderStatus);

    @Query(value = "select orderboost from OrderBoost  orderboost where orderboost.status = 'NEW'")
    List<OrderBoost> getFreeOrderBoosts();

    @Query(value = "select orderboost from OrderBoost orderboost where orderboost.status = 'COMPLETED' and orderboost.booster.id =:id")
    List<OrderBoost> findDoneOrderBoost(@Param("id") Long id);
}
