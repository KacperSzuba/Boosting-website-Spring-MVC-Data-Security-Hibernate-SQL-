package pl.javastart.repository;

import org.springframework.data.repository.CrudRepository;
import pl.javastart.model.entity.Message;
import pl.javastart.model.entity.User;

import java.util.List;

public interface MessageRepository extends CrudRepository<Message,Long> {
    List<Message> findAllByUserAndUser2(User user,User user2);
    Message findTopByOrderByIdDesc();
}
