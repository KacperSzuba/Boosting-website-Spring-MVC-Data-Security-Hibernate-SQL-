package com.BoostingWebsite.utils;

import org.springframework.stereotype.Repository;

@Repository
class ConstRepositoryImpl implements ConstRepository{
    private final SqlConstRepository repository;

    ConstRepositoryImpl(SqlConstRepository repository) {
        this.repository = repository;
    }

    @Override
    public Const findByEnumConst(EnumConst enumConst) {
        return Const.restore(repository.findByEnumConst(enumConst));
    }
}
