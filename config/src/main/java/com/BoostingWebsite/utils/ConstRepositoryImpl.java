package com.BoostingWebsite.utils;

import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
class ConstRepositoryImpl implements ConstRepository{
    private final SqlConstRepository repository;

    ConstRepositoryImpl(SqlConstRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<Const> findByEnumConst(EnumConst enumConst) {
        return repository.findByEnumConst(enumConst).map(Const::restore);
    }
}
