package com.BoostingWebsite.order;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

interface SqlOrderExtrasRepository extends CrudRepository<OrderExtrasSnapshot, Long> {
    List<OrderExtrasSnapshot> findAll();
}
