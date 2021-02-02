package com.BoostingWebsite.order.enumeration;

public enum EnumOrderStatus {

    NEW("New"),
    IN_PROGRESS("In progress"),
    COMPLETED("Completed");

    private final String name;

    EnumOrderStatus(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }
}
