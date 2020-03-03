package com.BoostingWebsite.order.division.repository;

import org.springframework.data.repository.CrudRepository;
import com.BoostingWebsite.order.division.entity.Price;

public interface PriceRepository extends CrudRepository<Price,Long> {
}
