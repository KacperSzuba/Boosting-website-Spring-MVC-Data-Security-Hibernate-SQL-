package com.BoostingWebsite.order;

import org.springframework.data.annotation.PersistenceConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "order_extras")
public class OrderExtras {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @PersistenceConstructor
    protected OrderExtras(){
    }

    Long getId() {
        return id;
    }

    String getName() {
        return name;
    }
}
