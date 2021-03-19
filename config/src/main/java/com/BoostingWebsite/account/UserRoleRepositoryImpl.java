package com.BoostingWebsite.account;

import org.springframework.stereotype.Repository;

@Repository
class UserRoleRepositoryImpl implements UserRoleRepository {
    private final SqlUserRoleRepository repository;

    UserRoleRepositoryImpl(SqlUserRoleRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserRole getUserRole(RoleName role) {
        return UserRole.restore(repository.getUserRole(role));
    }

    @Override
    public UserRole save(UserRole userRole) {
        return UserRole.restore(repository.save(userRole.getSnapshot()));
    }
}
