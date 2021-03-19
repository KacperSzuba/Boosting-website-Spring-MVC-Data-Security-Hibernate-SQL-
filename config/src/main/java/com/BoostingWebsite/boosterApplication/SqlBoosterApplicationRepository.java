package com.BoostingWebsite.boosterApplication;

import org.springframework.data.repository.CrudRepository;

interface SqlBoosterApplicationRepository extends CrudRepository<BoosterApplicationSnapshot, Long> {
    BoosterApplicationSnapshot save(BoosterApplicationSnapshot boosterApplication);
}
