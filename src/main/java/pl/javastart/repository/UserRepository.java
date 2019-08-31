package pl.javastart.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import pl.javastart.model.entity.User;

public interface UserRepository extends CrudRepository<User,Long> {
    User findByUsername(String username);
    boolean existsUserByUsername(String username);

    @Transactional
    @Modifying
    @Query(value = "UPDATE User u SET u.enabled =:enabled where u.username=:username")
    void updateEnabledStatement(@Param("username") String username,@Param("enabled") boolean enabled);

    @Transactional
    @Modifying
    @Query(value = "UPDATE User u SET u.enabled =:enabled where u.id=:id")
    void updateEnabledStatement2(@Param("id") Long id,@Param("enabled") boolean enabled);
}
