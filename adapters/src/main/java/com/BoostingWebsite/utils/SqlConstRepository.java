package com.BoostingWebsite.utils;

import org.springframework.data.repository.CrudRepository;

interface SqlConstRepository extends ConstRepository, CrudRepository<Const, Long> {
    Const findByEnumConst(EnumConst enumConst);
}
