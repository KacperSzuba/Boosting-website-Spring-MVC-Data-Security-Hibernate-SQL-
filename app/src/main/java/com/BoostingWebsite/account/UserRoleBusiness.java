package com.BoostingWebsite.account;

import com.BoostingWebsite.account.exception.UserRoleNotFoundException;
import com.BoostingWebsite.utils.BaseBusiness;

class UserRoleBusiness extends BaseBusiness {

    private final UserRoleRepository userRoleRepository;

    UserRoleBusiness(final UserRoleRepository userRoleRepository) {
        this.userRoleRepository = userRoleRepository;
    }

    UserRole save(RoleName roleName) throws UserRoleNotFoundException {
        UserRole userRole = findByRoleName(roleName);
        userRoleRepository.save(userRole);
        return userRole;
    }

    UserRole findByRoleName(RoleName roleName) throws UserRoleNotFoundException {
        return userRoleRepository.getUserRole(roleName)
                .orElseThrow(() -> new UserRoleNotFoundException("User role not found!"));
    }
}
