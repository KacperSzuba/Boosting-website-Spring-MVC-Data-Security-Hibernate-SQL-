package com.BoostingWebsite.utils;

import java.util.Optional;

interface ConstRepository {
    Optional<Const> findByEnumConst(EnumConst enumConst);
}
