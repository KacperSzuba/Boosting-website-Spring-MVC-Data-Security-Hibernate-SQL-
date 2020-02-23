package pl.javastart.repository.message;

import org.springframework.data.repository.CrudRepository;
import pl.javastart.model.entity.message.Message;
import pl.javastart.model.entity.user.User;

import java.util.List;

public interface MessageRepository extends CrudRepository<Message,Long> {
    List<Message> findAllByUserAndUser2(User user,User user2);
    Message findTopByOrderByIdDesc();
}