package com.BoostingWebsite.account;

import java.util.Optional;

interface UserRoleRepository {
    Optional<UserRole> getUserRole(RoleName role);

    UserRole save(UserRole userRole);
}