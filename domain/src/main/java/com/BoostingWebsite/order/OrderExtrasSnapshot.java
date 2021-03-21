package com.BoostingWebsite.order;

class OrderExtrasSnapshot {
    private Long id;
    private String name;

    protected OrderExtrasSnapshot() {}

    OrderExtrasSnapshot(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    Long getId() {
        return id;
    }

    String getName() {
        return name;
    }
}
