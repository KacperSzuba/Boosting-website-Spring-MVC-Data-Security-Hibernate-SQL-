package com.BoostingWebsite.order;

public enum QueueType {
    RANKED_SOLO_DUO("Solo/Duo"),
    RANKED_FLEX("Flex");

    private String name;

    QueueType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
