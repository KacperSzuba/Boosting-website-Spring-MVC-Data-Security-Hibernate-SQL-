package com.BoostingWebsite.order.repository;

import com.BoostingWebsite.account.user.User;
import com.BoostingWebsite.order.entity.OrderBoost;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
public interface OrderBoostRepository extends CrudRepository<OrderBoost, Long> {
    @Transactional
    @Query(value = "select orderboost from OrderBoost orderboost where (orderboost.user =:user or orderboost.booster=:user) and orderboost.whetherDone = false")
    Optional<OrderBoost> findActiveBoost(@Param("user") User user);

    @Query(value = "select orderboost.id from OrderBoost orderboost where (orderboost.user =:user or orderboost.booster=:user) and orderboost.whetherDone = false")
    Optional<Long> findActiveBoostId(@Param("user") User user);

    @Query(value = "select orderboost from OrderBoost orderboost where (orderboost.user =:user or orderboost.booster=:user) and orderboost.whetherDone = false")
    Boolean whetherUserHasOrder(@Param("user") User user);

    boolean existsByBoosterOrUserAndWhetherDone(User booster, User user, boolean whetherDone);

    @Transactional
    Optional<OrderBoost> findByBoosterOrUserAndWhetherDone(User booster, User user, boolean whetherDone);

    @Query(value = "SELECT orderboost FROM OrderBoost orderboost WHERE orderboost.booster = null")
    List<OrderBoost> findOrderBoostByBoosterEqualsNull();

    @Query(value = "SELECT orderboost FROM OrderBoost orderboost WHERE orderboost.booster=:u and orderboost.whetherDone=false")
    Optional<OrderBoost> checkIfTheBoosterHasNoOrders(@Param("u") User u);

    @Query(value = "select orderboost from OrderBoost orderboost where orderboost.booster =:user and orderboost.whetherDone=false")
    OrderBoost findCurrentBoost(@Param("user") User user);

    @Transactional
    @Modifying
    @Query(value = "UPDATE OrderBoost orderboost SET orderboost.booster =:user where orderboost.id=:id")
    void findFreeOrderBoosts(@Param("id") Long id, @Param("user") User user);

    @Transactional
    @Modifying
    @Query(value = "UPDATE OrderBoost orderboost SET orderboost.whetherDone =true where orderboost.id=:id")
    void setOrderAsDone(@Param("id") Long id);

    @Query(value = "select orderboost from OrderBoost orderboost where orderboost.whetherDone=true and orderboost.booster =:user")
    List<OrderBoost> findDoneOrderBoost(@Param("user") User user);
}