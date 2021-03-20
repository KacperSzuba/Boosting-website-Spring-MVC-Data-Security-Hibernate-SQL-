package com.BoostingWebsite.utils;

import org.springframework.data.repository.CrudRepository;

interface SqlConstRepository extends CrudRepository<ConstSnapshot, Long> {
    ConstSnapshot findByEnumConst(EnumConst enumConst);
}
