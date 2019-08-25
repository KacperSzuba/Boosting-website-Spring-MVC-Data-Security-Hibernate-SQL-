package pl.javastart.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import pl.javastart.model.UserRole;


public interface UserRoleRepository extends CrudRepository<UserRole,Long>{

    @Query(value = "SELECT ur FROM UserRole ur WHERE ur.roleName = :role")
    UserRole getUserRole(@Param("role") String role);
}
