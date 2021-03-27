package com.BoostingWebsite.account;

import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
class UserRepositoryImpl implements UserRepository {
    private final SqlUserRepository repository;

    UserRepositoryImpl(SqlUserRepository sqlUserRepository) {
        this.repository = sqlUserRepository;
    }

    @Override
    public Optional<User> findByUsernameOrEmail(String usernameOrEmail) {
        return repository.findByUsernameOrEmail(usernameOrEmail).map(User::restore);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return repository.findByEmail(email).map(User::restore);
    }

    @Override
    public boolean existsUserByUsername(String username) {
        return repository.existsUserByUsername(username);
    }

    @Override
    public Optional<UserRole> getUserRole(Long id) {
        return repository.getUserRole(id);
    }

    @Override
    public Optional<User> findById(Long id) {
        return repository.findById(id).map(User::restore);
    }

    @Override
    public User save(User user) {
        return User.restore(repository.save(user.getSnapshot()));
    }
}
