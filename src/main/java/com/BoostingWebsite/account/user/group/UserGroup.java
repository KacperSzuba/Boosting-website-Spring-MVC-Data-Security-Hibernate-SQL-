package com.BoostingWebsite.account.user.group;

import javax.persistence.*;

@Entity
@Table(name = "user_groups")
public class UserGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private UserGroups userGroups;

    public UserGroup() {}

    public Long getId() {
        return id;
    }

    public UserGroups getUserGroups() {
        return userGroups;
    }
}
