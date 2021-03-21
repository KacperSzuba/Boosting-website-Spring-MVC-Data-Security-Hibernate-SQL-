package com.BoostingWebsite.order;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
class OrderBoostQueryRepositoryImpl implements OrderBoostQueryRepository {
    private final SqlOrderBoostQueryRepository repository;

    OrderBoostQueryRepositoryImpl(SqlOrderBoostQueryRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<OrderBoost> findActiveBoost(Long id) {
        return repository.findActiveBoost(id).map(OrderBoost::restore);
    }

    @Override
    public boolean findByUser_IdOrBooster_IdAndStatus(Long userId, Long boosterId, EnumOrderStatus status) {
        return repository.findByUser_IdOrBooster_IdAndStatus(userId, boosterId, status);
    }

    @Override
    public boolean existsByBooster_IdOrUser_IdAndStatus(Long boosterId, Long userId, EnumOrderStatus orderStatus) {
        return repository.existsByBooster_IdOrUser_IdAndStatus(boosterId, userId, orderStatus);
    }

    @Override
    public List<OrderBoost> getFreeOrderBoosts() {
        return repository.getFreeOrderBoosts().stream().map(OrderBoost::restore).collect(Collectors.toList());
    }

    @Override
    public List<OrderBoost> findDoneOrderBoost(Long id) {
        return repository.findDoneOrderBoost(id).stream().map(OrderBoost::restore).collect(Collectors.toList());
    }

    @Override
    public OrderBoost save(OrderBoost orderBoost) {
        return OrderBoost.restore(repository.save(orderBoost.getSnapshot()));
    }
}
