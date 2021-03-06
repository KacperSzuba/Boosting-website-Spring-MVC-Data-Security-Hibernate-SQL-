package com.BoostingWebsite.account;

public class UserRoleFacade {

    private final UserRoleRepository userRoleRepository;

    public UserRoleFacade(final UserRoleRepository userRoleRepository) {
        this.userRoleRepository = userRoleRepository;
    }

    public UserRole save(RoleName roleName){
        UserRole userRole = userRoleRepository.getUserRole(roleName);
        userRoleRepository.save(userRole);

        return userRole;
    }
}
