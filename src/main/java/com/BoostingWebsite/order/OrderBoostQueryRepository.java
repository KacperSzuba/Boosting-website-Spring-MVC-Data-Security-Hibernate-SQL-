package com.BoostingWebsite.order;

import com.BoostingWebsite.order.enumeration.EnumOrderStatus;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface OrderBoostQueryRepository{
    Optional<OrderBoost> findActiveBoost(@Param("id") Long id);

    boolean findByUser_IdOrBooster_IdAndStatus(@Param("userId") Long userId, @Param("boosterId") Long boosterId, @Param("status") EnumOrderStatus status);

    boolean existsByBooster_IdOrUser_IdAndStatus(Long boosterId, Long userId, EnumOrderStatus orderStatus);

    List<OrderBoost> getFreeOrderBoosts();

    List<OrderBoost> findDoneOrderBoost(@Param("id") Long id);

    OrderBoost save(OrderBoost orderBoost);
}
