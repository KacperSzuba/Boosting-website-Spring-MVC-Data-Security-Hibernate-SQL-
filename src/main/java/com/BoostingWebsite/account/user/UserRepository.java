package com.BoostingWebsite.account.user;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import com.BoostingWebsite.account.roles.UserRole;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User,Long> {
    User findByUsername(String username);

    boolean existsUserByEmail(String email);

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

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM User u WHERE u.id=:id")
    void deleteUser(@Param("id")Long id);

    @Query(value = "SELECT u.roles FROM User u WHERE u.id = :id")
    UserRole getUserRole(@Param("id") Long id);

}