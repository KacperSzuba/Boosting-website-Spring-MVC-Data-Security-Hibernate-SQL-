package pl.javastart.message;

import org.springframework.stereotype.Service;
import pl.javastart.manage.ActualUser;
import pl.javastart.model.entity.Message;
import pl.javastart.model.entity.OrderBoost;
import pl.javastart.repository.MessageRepository;
import pl.javastart.repository.OrderBoostRepository;
import pl.javastart.repository.UserRepository;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MessageHandler {
    private final MessageRepository messageRepository;

    private final ActualUser actualUser;

    private final UserRepository userRepository;

    private final OrderBoostRepository orderBoostRepository;

    public MessageHandler(MessageRepository messageRepository, ActualUser actualUser, UserRepository userRepository, OrderBoostRepository orderBoostRepository) {
        this.messageRepository = messageRepository;
        this.actualUser = actualUser;
        this.userRepository = userRepository;
        this.orderBoostRepository = orderBoostRepository;
    }

    //Rozbić na funkcje
    public void sendMessage(HttpServletRequest request, Message message, String username){
        Optional<OrderBoost> orderBoost = orderBoostRepository.findOrderBoostByUserOrBooster(actualUser.getActualUser(request));
        System.out.println(orderBoost.get().getBooster().getUsername());
        switch (username) {
            case "Admin123x":
                message.setUser2(userRepository.findByUsername("Admin123x"));
                break;
            case "booster":
                orderBoost.ifPresent(boost -> message.setUser2(boost.getBooster()));
                break;
            case "customer":
                orderBoost.ifPresent(boost -> message.setUser2(boost.getUser()));
                break;
        }
        Message messageDB = new Message(actualUser.getActualUser(request),message.getUser2(),message.getTitle(), message.getMessage());
        messageRepository.save(messageDB);
    }

    public List<String> listOfMessages(HttpServletRequest request){
        List<String> listOfMessages = new ArrayList<>();
            for (Message message : messageRepository.findAll()) {
                if (message.getUser2().getUsername().equals(actualUser.getActualUser(request).getUsername())) {
                    listOfMessages.add(message.getMessage());
                }
            }
        return listOfMessages;
    }

}