package pl.javastart.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import pl.javastart.model.UserRole;

import java.util.List;

public interface UserRoleRepository extends CrudRepository<UserRole,Long>{

    @Query(value = "SELECT ur FROM UserRole ur WHERE ur.roleName = 'ROLE_USER'")
    UserRole getUserRole();
}
