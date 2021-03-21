package com.BoostingWebsite.order;

public class OrderExtras {
    private Long id;
    private String name;

    private OrderExtras(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    static OrderExtras restore(OrderExtrasSnapshot snapshot){
        return new OrderExtras(snapshot.getId(), snapshot.getName());
    }

    OrderExtrasSnapshot getSnapshot(){
        return new OrderExtrasSnapshot(id, name);
    }
}
