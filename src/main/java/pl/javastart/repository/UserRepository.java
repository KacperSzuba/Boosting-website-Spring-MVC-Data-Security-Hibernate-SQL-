package pl.javastart.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import pl.javastart.model.entity.User;
import pl.javastart.model.entity.UserRole;

public interface UserRepository extends CrudRepository<User,Long> {
    User findByUsername(String username);
    boolean existsUserByUsername(String username);
    @Transactional
    @Modifying
    @Query(value = "UPDATE User u SET u.enabled =:enabled where u.id=:id")
    void changeEnabledStatementQuery(@Param("id") Long id, @Param("enabled") boolean enabled);

    @Transactional
    @Modifying
    @Query(value = "UPDATE User u SET u.password =:password where u.id=:id")
    void changePasswordQuery(@Param("id") Long id,@Param("password") String password);

    @Transactional
    @Modifying
    @Query(value = "UPDATE User u SET u.email =:email where u.id=:id")
    void changeEmailQuery(@Param("id") Long id,@Param("email") String email);

}
