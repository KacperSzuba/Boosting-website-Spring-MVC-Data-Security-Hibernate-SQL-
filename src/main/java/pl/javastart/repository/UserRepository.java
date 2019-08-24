package pl.javastart.repository;

import org.springframework.data.repository.CrudRepository;
import pl.javastart.model.User;

public interface UserRepository extends CrudRepository<User,Long> {
    User findByUsername(String username);
}
