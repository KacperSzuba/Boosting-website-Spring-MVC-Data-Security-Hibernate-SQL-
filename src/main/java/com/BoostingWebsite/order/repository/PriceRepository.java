package com.BoostingWebsite.order.repository;

import com.BoostingWebsite.order.entity.Price;
import org.springframework.data.repository.CrudRepository;

public interface PriceRepository extends CrudRepository<Price,Long> {
}
