package com.BoostingWebsite.utils;

import com.BoostingWebsite.utils.exception.ConstNotFoundException;

public class ConstBusiness extends BaseBusiness {

    private final ConstRepository constRepository;

    public ConstBusiness(final ConstRepository constRepository) {
        this.constRepository = constRepository;
    }

    public String getLeagueOfLegendsApiKey() throws ConstNotFoundException {
        return findByEnumCost(EnumConst.LOL_API_KEY).getSnapshot().getValue();
    }

    Const findByEnumCost(final EnumConst enumConst) throws ConstNotFoundException {
        return constRepository.findByEnumConst(enumConst)
                .orElseThrow(() -> new ConstNotFoundException("Const not found!"));
    }
}
