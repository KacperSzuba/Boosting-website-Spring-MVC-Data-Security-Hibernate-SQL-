package com.BoostingWebsite.utils;

public class ConstBusiness extends BaseBusiness {

    private final ConstRepository constRepository;

    public ConstBusiness(final ConstRepository constRepository) {
        this.constRepository = constRepository;
    }

    public String getLeagueOfLegendsApiKey(){
        return constRepository.findByEnumConst(EnumConst.LOL_API_KEY).getSnapshot().getValue();
    }
}
