package com.BoostingWebsite.order.entity;

public enum EnumOrderStatus {

    NEW("New"),
    IN_PROGRESS("In progress"),
    COMPLETED("Completed");

    private String name;

    EnumOrderStatus(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }
}
