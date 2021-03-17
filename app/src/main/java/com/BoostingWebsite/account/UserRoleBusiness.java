package com.BoostingWebsite.account;

import com.BoostingWebsite.utils.BaseFacade;

public class UserRoleBusiness extends BaseFacade {

    private final UserRoleRepository userRoleRepository;

    public UserRoleBusiness(final UserRoleRepository userRoleRepository) {
        this.userRoleRepository = userRoleRepository;
    }

    public UserRole save(RoleName roleName){
        UserRole userRole = userRoleRepository.getUserRole(roleName);
        userRoleRepository.save(userRole);

        return userRole;
    }
}
