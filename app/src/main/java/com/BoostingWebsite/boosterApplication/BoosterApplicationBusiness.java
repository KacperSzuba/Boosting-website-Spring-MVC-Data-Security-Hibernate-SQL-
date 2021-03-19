package com.BoostingWebsite.boosterApplication;

class BoosterApplicationBusiness {
    private final BoosterApplicationRepository repository;

    BoosterApplicationBusiness(BoosterApplicationRepository repository) {
        this.repository = repository;
    }

    void save(BoosterApplication boosterApplication){
        repository.save(boosterApplication);
    }
}
