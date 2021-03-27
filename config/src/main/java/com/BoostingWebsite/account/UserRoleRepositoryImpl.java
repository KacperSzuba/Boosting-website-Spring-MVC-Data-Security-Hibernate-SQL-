package com.BoostingWebsite.account;

import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
class UserRoleRepositoryImpl implements UserRoleRepository {
    private final SqlUserRoleRepository repository;

    UserRoleRepositoryImpl(SqlUserRoleRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<UserRole> getUserRole(RoleName role) {
        return repository.getUserRole(role).map(UserRole::restore);
    }

    @Override
    public UserRole save(UserRole userRole) {
        return UserRole.restore(repository.save(userRole.getSnapshot()));
    }
}
