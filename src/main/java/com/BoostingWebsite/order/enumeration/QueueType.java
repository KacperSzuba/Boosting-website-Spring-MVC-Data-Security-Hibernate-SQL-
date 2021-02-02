package com.BoostingWebsite.order.enumeration;

public enum QueueType {
    RANKED_SOLO_DUO("Solo/Duo"),
    RANKED_FLEX("Flex");

    private final String name;

    QueueType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
