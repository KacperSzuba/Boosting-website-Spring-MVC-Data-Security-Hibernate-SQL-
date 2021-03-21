package com.BoostingWebsite.order;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
class OrderExtrasRepositoryImpl implements OrderExtrasRepository{
    private final SqlOrderExtrasRepository repository;

    OrderExtrasRepositoryImpl(SqlOrderExtrasRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<OrderExtras> findAll() {
        return repository.findAll().stream().map(OrderExtras::restore).collect(Collectors.toList());
    }
}
