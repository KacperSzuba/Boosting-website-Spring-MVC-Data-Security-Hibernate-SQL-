package com.BoostingWebsite.account;

import org.hibernate.annotations.Immutable;
import org.springframework.data.annotation.PersistenceConstructor;

import javax.persistence.*;

@Entity
@Table(name = "user_roles")
@Immutable
class UserRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Enumerated(EnumType.STRING)
    private RoleName roleName;

    @PersistenceConstructor
    UserRole() {
    }

    UserRole(RoleName roleName) {
        this.roleName = roleName;
    }

    int getId() {
        return id;
    }

    RoleName getRoleName() {
        return roleName;
    }

}
