package com.BoostingWebsite.order.division.repository;

import org.springframework.data.repository.CrudRepository;
import com.BoostingWebsite.order.division.entity.TierImage;

public interface TierImageRepository extends CrudRepository<TierImage,Long> {
    TierImage findByImgSource(String imgSource);
}

