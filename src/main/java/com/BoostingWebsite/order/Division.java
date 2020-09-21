package com.BoostingWebsite.order;

public enum Division {
    Division_1("Division 1", 4),
    Division_2("Division 2", 3),
    Division_3("Division 3", 2),
    Division_4("Division 4", 1);

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
