package com.BoostingWebsite.message;

import org.springframework.data.repository.CrudRepository;
import com.BoostingWebsite.account.user.User;

import java.util.List;

public interface MessageRepository extends CrudRepository<Message,Long> {
    List<Message> findAllByUserAndUser2(User user, User user2);
    Message findTopByOrderByIdDesc();
}