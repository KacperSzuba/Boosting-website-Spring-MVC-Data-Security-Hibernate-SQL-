package com.BoostingWebsite.order.division2.repository;

import com.BoostingWebsite.account.user.User;
import com.BoostingWebsite.order.OrderBoost;
import com.BoostingWebsite.order.division2.entity.OrderBoost2;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface OrderBoostRepository2 extends CrudRepository<OrderBoost2,Long> {

    @Query(value = "select orderboost from OrderBoost2 orderboost where (orderboost.user =:user or orderboost.booster=:user) and orderboost.whetherDone=false")
    Optional<OrderBoost2> findOrderBoostByUserOrBooster(@Param("user") User user);

    @Query(value = "SELECT orderboost FROM OrderBoost2 orderboost WHERE orderboost.booster = null")
    List<OrderBoost2> findOrderBoostByBoosterEqualsNull();

    @Query(value = "SELECT orderboost FROM OrderBoost2 orderboost WHERE orderboost.booster=:u and orderboost.whetherDone=false")
    Optional<OrderBoost> checkIfTheBoosterHasNoOrders(@Param("u") User u);

    @Query(value = "select orderboost from OrderBoost2 orderboost where orderboost.booster =:user and orderboost.whetherDone=false")
    OrderBoost findCurrentBoost(@Param("user")User user);

    @Transactional
    @Modifying
    @Query(value = "UPDATE OrderBoost2 orderboost SET orderboost.booster =:user where orderboost.id=:id")
    void findFreeOrderBoosts(@Param("id") Long id, @Param("user")User user);

    @Transactional
    @Modifying
    @Query(value = "UPDATE OrderBoost2 orderboost SET orderboost.whetherDone =true where orderboost.id=:id")
    void setOrderAsDone(@Param("id") Long id);

    @Query(value = "select orderboost from OrderBoost2 orderboost where orderboost.whetherDone=true and orderboost.booster =:user")
    List<OrderBoost2> findDoneOrderBoost(@Param("user") User user);
}
