package com.BoostingWebsite.order;

public enum Tier {
    IRON("Iron", 10),
    BRONZE("Bronze", 20),
    SILVER("Silver", 30),
    GOLD("Gold", 40),
    PLATINUM("Platinum", 50),
    DIAMOND("Diamond", 60);

    private String name;
    private int priority;

    Tier(String name, int priority) {
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
