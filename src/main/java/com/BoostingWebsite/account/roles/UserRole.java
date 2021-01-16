package com.BoostingWebsite.account.roles;

import org.hibernate.annotations.Immutable;
import org.springframework.data.annotation.PersistenceConstructor;

import javax.persistence.*;

@Entity
@Table(name = "user_roles")
@Immutable
public class UserRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Enumerated(EnumType.STRING)
    private RoleName roleName;

    @PersistenceConstructor
    public UserRole() {
    }

    public UserRole(RoleName roleName) {
        this.roleName = roleName;
    }

    public int getId() {
        return id;
    }

    public RoleName getRoleName() {
        return roleName;
    }

}
