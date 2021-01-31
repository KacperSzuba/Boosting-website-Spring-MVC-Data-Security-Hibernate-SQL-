package com.BoostingWebsite.order;

public enum Division {
    Division_1("I", 4),
    Division_2("II", 3),
    Division_3("III", 2),
    Division_4("IV", 1);

    public final static String PLACEMENTS = "Placements";

    private final String name;
    private final int priority;

    Division(String name, int priority){
        this.name = name;
        this.priority = priority;
    }

    public static Division getDivision(String name){
        switch (name) {
            case "I": return Division.Division_1;
            case "II": return Division.Division_2;
            case "III": return Division.Division_3;
            case "IV": return Division.Division_4;
            default: throw new IllegalArgumentException(name);
        }
    }

    public String getName() {
        return name;
    }

    public int getPriority() {
        return priority;
    }
}
