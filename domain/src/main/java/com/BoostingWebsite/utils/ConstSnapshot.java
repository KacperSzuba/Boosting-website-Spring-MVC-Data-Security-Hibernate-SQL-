package com.BoostingWebsite.utils;

class ConstSnapshot {
    private Long id;
    private EnumConst enumConst;
    private String value;

    protected ConstSnapshot(){}

    ConstSnapshot(Long id, EnumConst enumConst, String value) {
        this.id = id;
        this.enumConst = enumConst;
        this.value = value;
    }

    Long getId() {
        return id;
    }

    EnumConst getEnumConst() {
        return enumConst;
    }

    String getValue() {
        return value;
    }
}
