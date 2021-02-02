package com.BoostingWebsite.order.enumeration;

public enum LPGainPerWin {
    LESS_THAN_15("Less than 15"),
    BETWEEN_15_AND_16("15-16"),
    BETWEEN_17_AND_19("17-19"),
    MORE_THAN_20("20+");

    private final String name;

    LPGainPerWin(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
