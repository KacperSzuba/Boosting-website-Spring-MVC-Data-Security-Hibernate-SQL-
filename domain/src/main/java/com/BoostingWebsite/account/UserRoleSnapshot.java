package com.BoostingWebsite.account;

class UserRoleSnapshot {
    private Long id;
    private RoleName roleName;

    protected UserRoleSnapshot(){}

    UserRoleSnapshot(Long id, RoleName roleName) {
        this.id = id;
        this.roleName = roleName;
    }

    Long getId() {
        return id;
    }

    RoleName getRoleName() {
        return roleName;
    }
}
