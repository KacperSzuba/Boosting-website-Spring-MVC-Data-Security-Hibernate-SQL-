package pl.javastart.repository;

import org.springframework.data.repository.CrudRepository;
import pl.javastart.model.entity.Message;

public interface MessageRepository extends CrudRepository<Message,Long> {
}
