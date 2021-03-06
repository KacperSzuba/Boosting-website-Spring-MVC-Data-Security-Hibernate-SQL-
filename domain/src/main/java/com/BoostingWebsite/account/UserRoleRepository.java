package com.BoostingWebsite.account;

import org.springframework.data.repository.query.Param;

interface UserRoleRepository {
    UserRole getUserRole(@Param("role") RoleName role);

    UserRole save(UserRole userRole);
}