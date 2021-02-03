package com.BoostingWebsite.utils;

import org.springframework.stereotype.Service;

@Service
public class ConstFacade {

    private final ConstRepository constRepository;

    public ConstFacade(ConstRepository constRepository) {
        this.constRepository = constRepository;
    }

    public String getLeagueOfLegendsApiKey(){
        return constRepository.findByEnumConst(EnumConst.LOL_API_KEY).getValue();
    }
}
