package pl.javastart.message;

import org.springframework.stereotype.Service;
import pl.javastart.manage.ActualUser;
import pl.javastart.model.entity.Message;
import pl.javastart.model.entity.OrderBoost;
import pl.javastart.repository.MessageRepository;
import pl.javastart.repository.OrderBoostRepository;
import pl.javastart.repository.UserRepository;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

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
        Message messageDB = new Message(message.getTitle(), message.getMessage(),actualUser.getActualUser(request),message.getUser2());
        messageRepository.save(messageDB);
    }

    public void sendMessage(Message message){
        List<Message> listOfMessages = new ArrayList<>();
        for(Message message2 :messageRepository.findAll()){
            if(message2.getUser2().getUsername().equals("Admin123x")){
                listOfMessages.add(message2);
            }
        }
    }
    
    public Set<Long> setOfRecipientId(HttpServletRequest request){
        Set<Long> recipientId = new TreeSet<>();
        for (Message message : messageRepository.findAll()) {
            if (Objects.equals(message.getUser2().getId(), actualUser.getActualUser(request).getId())) {
                recipientId.add(message.getUser().getId());
            }
        }
        return recipientId;
    }
    public Set<Message> listOfMessages3(HttpServletRequest request){
        Set<Message> usernames = new HashSet<>();
        for (Message message : messageRepository.findAll()) {
            if (Integer.parseInt(String.valueOf(message.getUser2().getId())) == Integer.parseInt(String.valueOf(actualUser.getActualUser(request).getId()))) {
                usernames.add(message);
            }
        }
        return usernames;
    }
    
}