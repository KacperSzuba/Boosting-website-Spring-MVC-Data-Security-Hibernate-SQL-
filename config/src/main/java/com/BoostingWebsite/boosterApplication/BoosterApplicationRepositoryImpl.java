package com.BoostingWebsite.boosterApplication;

import org.springframework.stereotype.Repository;

@Repository
class BoosterApplicationRepositoryImpl implements BoosterApplicationRepository{
    private final SqlBoosterApplicationRepository repository;

    BoosterApplicationRepositoryImpl(SqlBoosterApplicationRepository repository) {
        this.repository = repository;
    }

    @Override
    public BoosterApplication save(BoosterApplication boosterApplication) {
        return BoosterApplication.restore(repository.save(boosterApplication.getSnapshot()));
    }
}
