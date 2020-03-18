package com.BoostingWebsite.account.roles;

import org.hibernate.annotations.Immutable;

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
