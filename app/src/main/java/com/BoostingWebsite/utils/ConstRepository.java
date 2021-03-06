package com.BoostingWebsite.utils;

import org.springframework.data.jpa.repository.JpaRepository;

interface ConstRepository extends JpaRepository<Const, Long> {
    Const findByEnumConst(EnumConst enumConst);
}
