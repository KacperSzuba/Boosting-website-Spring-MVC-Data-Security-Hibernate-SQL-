package com.BoostingWebsite.account;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

interface SqlUserRoleRepository extends UserRoleRepository, CrudRepository<UserRole, Long> {
    @Query(value = "SELECT ur FROM UserRole ur WHERE ur.roleName = :role")
    UserRole getUserRole(@Param("role") RoleName role);
}
