package pl.javastart.repository.user;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import pl.javastart.model.entity.user.UserRole;
import pl.javastart.model.enums.RoleName;


public interface UserRoleRepository extends CrudRepository<UserRole,Long>{
    @Query(value = "SELECT ur FROM UserRole ur WHERE ur.roleName = :role")
    UserRole getUserRole(@Param("role") RoleName role);
}