package com.BoostingWebsite.order;

public enum Division {
    Division_1("I", 4),
    Division_2("II", 3),
    Division_3("III", 2),
    Division_4("IV", 1);

    public final static String PLACEMENTS = "Placements";

    private String name;
    private int priority;

    Division(String name, int priority){
        this.name = name;
        this.priority = priority;
    }

    public String getName() {
        return name;
    }

    public int getPriority() {
        return priority;
    }
}
