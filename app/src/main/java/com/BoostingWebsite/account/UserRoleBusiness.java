package com.BoostingWebsite.account;

import com.BoostingWebsite.utils.BaseBusiness;

class UserRoleBusiness extends BaseBusiness {

    private final UserRoleRepository userRoleRepository;

    UserRoleBusiness(final UserRoleRepository userRoleRepository) {
        this.userRoleRepository = userRoleRepository;
    }

    UserRole save(RoleName roleName){
        UserRole userRole = userRoleRepository.getUserRole(roleName);
        userRoleRepository.save(userRole);

        return userRole;
    }
}
