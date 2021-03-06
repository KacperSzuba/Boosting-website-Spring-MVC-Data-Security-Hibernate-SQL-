package com.BoostingWebsite.utils;

public class ConstFacade {

    private final ConstRepository constRepository;

    public ConstFacade(final ConstRepository constRepository) {
        this.constRepository = constRepository;
    }

    public String getLeagueOfLegendsApiKey(){
        return constRepository.findByEnumConst(EnumConst.LOL_API_KEY).getValue();
    }
}
