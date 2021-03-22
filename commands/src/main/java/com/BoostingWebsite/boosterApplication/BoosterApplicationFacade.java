package com.BoostingWebsite.boosterApplication;

import com.BoostingWebsite.boosterApplication.dto.BoosterApplicationDto;
import com.BoostingWebsite.utils.BaseFacade;

class BoosterApplicationFacade extends BaseFacade {
    private final BoosterApplicationBusiness boosterApplicationBusiness;
    private final BoosterApplicationFactory boosterApplicationFactory;

    BoosterApplicationFacade(BoosterApplicationBusiness boosterApplicationBusiness, BoosterApplicationFactory boosterApplicationFactory) {
        this.boosterApplicationBusiness = boosterApplicationBusiness;
        this.boosterApplicationFactory = boosterApplicationFactory;
    }

    void save(BoosterApplicationDto boosterApplicationDto){
        boosterApplicationBusiness.save(boosterApplicationFactory.from(boosterApplicationDto));
    }
}
