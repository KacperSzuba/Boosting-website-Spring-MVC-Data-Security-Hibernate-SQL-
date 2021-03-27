package com.BoostingWebsite.utils;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

interface SqlConstRepository extends CrudRepository<ConstSnapshot, Long> {
    Optional<ConstSnapshot> findByEnumConst(EnumConst enumConst);
}
