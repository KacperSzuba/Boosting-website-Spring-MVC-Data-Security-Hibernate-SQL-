package com.BoostingWebsite.account;

class UserRole {
    private Long id;
    private RoleName roleName;

    private UserRole(Long id, RoleName roleName) {
        this.id = id;
        this.roleName = roleName;
    }

    static UserRole restore(UserRoleSnapshot snapshot){
        return new UserRole(snapshot.getId(), snapshot.getRoleName());
    }

    UserRoleSnapshot getSnapshot(){
        return new UserRoleSnapshot(id, roleName);
    }
}
