package com.BoostingWebsite.account;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

interface SqlUserRoleRepository extends CrudRepository<UserRoleSnapshot, Long> {
    @Query(value = "SELECT ur FROM UserRoleSnapshot ur WHERE ur.roleName = :role")
    UserRoleSnapshot getUserRole(@Param("role") RoleName role);

    UserRoleSnapshot save(UserRoleSnapshot userRole);
}
