package com.BoostingWebsite.order;

import java.util.List;
import java.util.Optional;

public interface OrderBoostQueryRepository{
    Optional<OrderBoost> findActiveBoost(Long id);

    boolean findByUser_IdOrBooster_IdAndStatus(Long userId, Long boosterId, EnumOrderStatus status);

    boolean existsByBooster_IdOrUser_IdAndStatus(Long boosterId, Long userId, EnumOrderStatus orderStatus);

    List<OrderBoost> getFreeOrderBoosts();

    List<OrderBoost> findDoneOrderBoost(Long id);

    OrderBoost save(OrderBoost orderBoost);
}
