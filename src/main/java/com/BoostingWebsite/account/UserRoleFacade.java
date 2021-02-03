package com.BoostingWebsite.account;

import org.springframework.stereotype.Service;

@Service
public class UserRoleFacade {

    private final UserRoleRepository userRoleRepository;

    public UserRoleFacade(UserRoleRepository userRoleRepository) {
        this.userRoleRepository = userRoleRepository;
    }

    public UserRole save(RoleName roleName){
        UserRole userRole = userRoleRepository.getUserRole(roleName);
        userRoleRepository.save(userRole);

        return userRole;
    }
}
