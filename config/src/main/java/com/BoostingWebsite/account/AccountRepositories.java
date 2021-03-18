package com.BoostingWebsite.account;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

interface SqlLoginHistoryRepository extends CrudRepository<LoginHistorySnapshot, Long> {
    LoginHistorySnapshot save(LoginHistorySnapshot loginHistory);
}

@org.springframework.stereotype.Repository
class LoginHistoryRepositoryImpl implements LoginHistoryRepository {
    private final SqlLoginHistoryRepository repository;

    LoginHistoryRepositoryImpl(SqlLoginHistoryRepository repository) {
        this.repository = repository;
    }

    @Override
    public LoginHistory save(LoginHistory loginHistory) {
        return LoginHistory.restore(repository.save(loginHistory.getSnapshot()));
    }
}

interface SqlUserRepository extends CrudRepository<UserSnapshot, Long> {
    @Query(value = "SELECT user FROM UserSnapshot user WHERE user.username = :usernameOrEmail or user.email = :usernameOrEmail")
    Optional<UserSnapshot> findByUsernameOrEmail(String usernameOrEmail);

    Optional<UserSnapshot> findByEmail(String email);

    boolean existsUserByUsername(String username);

    @Query(value = "SELECT u.roles FROM UserSnapshot u WHERE u.id = :id")
    UserRole getUserRole(@Param("id") Long id);

    Optional<UserSnapshot> findById(Long id);

    UserSnapshot save(UserSnapshot user);
}

@org.springframework.stereotype.Repository
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
    public UserRole getUserRole(Long id) {
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

interface SqlUserRoleRepository extends CrudRepository<UserRoleSnapshot, Long> {
    @Query(value = "SELECT ur FROM UserRoleSnapshot ur WHERE ur.roleName = :role")
    UserRoleSnapshot getUserRole(@Param("role") RoleName role);

    UserRoleSnapshot save(UserRoleSnapshot userRole);
}

@org.springframework.stereotype.Repository
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

interface SqlUserTokenRepository extends CrudRepository<UserTokenSnapshot, Long> {
    Optional<UserTokenSnapshot> findByUser_Username(String username);

    UserTokenSnapshot findByToken(String token);

    void delete(UserTokenSnapshot userToken);

    UserTokenSnapshot save(UserTokenSnapshot userToken);
}


@org.springframework.stereotype.Repository
class UserTokenRepositoryImpl implements UserTokenRepository {
    private final SqlUserTokenRepository repository;

    UserTokenRepositoryImpl(SqlUserTokenRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<UserToken> findByUser_Username(String username) {
        return repository.findByUser_Username(username).map(UserToken::restore);
    }

    @Override
    public UserToken findByToken(String token) {
        return UserToken.restore(repository.findByToken(token));
    }

    @Override
    public void delete(UserToken userToken) {
        repository.delete(userToken.getSnapshot());
    }

    @Override
    public UserToken save(UserToken userToken) {
        return UserToken.restore(userToken.getSnapshot());
    }
}


interface SqlUserQueryRepository extends CrudRepository<UserSnapshot, Long> {
    boolean existsUserByEmail(String email);

    boolean existsUserByUsername(String username);

    UserSnapshot findByUsername(String username);

    Optional<UserSnapshot> getById(Long id);
}

@org.springframework.stereotype.Repository
class UserQueryRepositoryImpl implements UserQueryRepository {
    private final SqlUserQueryRepository repository;

    UserQueryRepositoryImpl(SqlUserQueryRepository repository) {
        this.repository = repository;
    }

    @Override
    public boolean existsUserByEmail(String email) {
        return repository.existsUserByEmail(email);
    }

    @Override
    public boolean existsUserByUsername(String username) {
        return repository.existsUserByUsername(username);
    }

    @Override
    public SimpleUserDto findByUsername(String username) {
        return null;
    }

    @Override
    public Optional<SimpleUserDto> getById(Long id) {
        return Optional.empty();
    }
}