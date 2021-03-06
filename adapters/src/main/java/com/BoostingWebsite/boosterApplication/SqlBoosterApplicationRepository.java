package com.BoostingWebsite.boosterApplication;

import org.springframework.data.repository.CrudRepository;

interface SqlBoosterApplicationRepository extends BoosterApplicationRepository, CrudRepository<BoosterApplication, Long> {
    BoosterApplication save(BoosterApplication boosterApplication);
}
