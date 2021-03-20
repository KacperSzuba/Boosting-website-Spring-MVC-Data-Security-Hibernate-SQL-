package com.BoostingWebsite.utils;

class Const {
    private Long id;
    private EnumConst enumConst;
    private String value;

    private Const(Long id, EnumConst enumConst, String value) {
        this.id = id;
        this.enumConst = enumConst;
        this.value = value;
    }

    static Const restore(ConstSnapshot snapshot){
        return new Const(snapshot.getId(), snapshot.getEnumConst(), snapshot.getValue());
    }

    ConstSnapshot getSnapshot(){
        return new ConstSnapshot(id, enumConst, value);
    }
}
