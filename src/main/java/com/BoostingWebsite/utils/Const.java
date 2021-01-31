package com.BoostingWebsite.utils;

import org.hibernate.annotations.Immutable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Immutable
@Table(name = "consts")
class Const {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "const")
    @Enumerated(EnumType.STRING)
    @NotNull
    private EnumConst enumConst;

    @NotNull
    private String name;

    Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public EnumConst getConst(){
        return enumConst;
    }
}
