package com.BoostingWebsite.order;

public enum Region {
    EUNE("eun1"),
    EUW("euw1"),
    RU("ru"),
    TR("tr1");

    private String value;

    Region(String value){
        this.value = value;
    }

    public String getValue(){
        return value;
    }
}
