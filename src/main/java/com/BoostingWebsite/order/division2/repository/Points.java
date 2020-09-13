package com.BoostingWebsite.order.division2.repository;

public enum Points {
    firstThreshold("0-19"),
    secondThreshold("20-39"),
    thirdThreshold("40-59"),
    fourthThreshold("60-79"),
    fifthThreshold("80-99");

    public final static String SERIES = "Series";
    private String name;

    Points(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
