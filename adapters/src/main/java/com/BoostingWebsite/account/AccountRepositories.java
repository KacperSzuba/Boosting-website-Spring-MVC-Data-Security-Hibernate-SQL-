package com.BoostingWebsite.account;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

interface SqlLoginHistoryRepository extends LoginHistoryRepository, CrudRepository<LoginHistory, Long> {
    LoginHistory save(LoginHistory loginHistory);
}

interface SqlUserRepository extends UserRepository, CrudRepository<User, Long> {
    User findByUsername(String username);

    Optional<User> findByEmail(String email);

    boolean existsUserByUsername(String username);

    @Transactional
    @Modifying
    @Query(value = "UPDATE User u SET u.enabled =:enabled WHERE u.id=:id")
    void changeUserEnabledStatement(@Param("id") Long id, @Param("enabled") boolean enabled);

    @Transactional
    @Modifying
    @Query(value = "UPDATE User u SET u.password =:password WHERE u.id=:id")
    void changePassword(@Param("id") Long id, @Param("password") String password);

    @Transactional
    @Modifying
    @Query(value = "UPDATE User u SET u.email =:email WHERE u.id=:id")
    void changeEmail(@Param("id") Long id, @Param("email") String email);

    @Query(value = "SELECT u.roles FROM User u WHERE u.id = :id")
    UserRole getUserRole(@Param("id") Long id);

    Optional<User> findById(Long id);

    User save(User user);
}

interface SqlUserRoleRepository extends UserRoleRepository, CrudRepository<UserRole, Long> {
    @Query(value = "SELECT ur FROM UserRole ur WHERE ur.roleName = :role")
    UserRole getUserRole(@Param("role") RoleName role);

    UserRole save(UserRole userRole);
}

interface SqlUserTokenRepository extends UserTokenRepository, CrudRepository<UserToken, Long> {
    Optional<UserToken> findByUser_Username(String username);

    UserToken findByToken(String token);

    void delete(UserToken userToken);

    UserToken save(UserToken userToken);
}