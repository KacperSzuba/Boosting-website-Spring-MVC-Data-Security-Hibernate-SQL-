package pl.javastart.message;

import org.springframework.stereotype.Service;
import pl.javastart.manage.ActualUser;
import pl.javastart.model.entity.Message;
import pl.javastart.model.entity.OrderBoost;
import pl.javastart.model.entity.User;
import pl.javastart.model.entity.enums.RoleName;
import pl.javastart.repository.MessageRepository;
import pl.javastart.repository.OrderBoostRepository;
import pl.javastart.repository.UserRepository;
import pl.javastart.service.ChangeAccountStatus;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Service
public class MessageHandler {
    private final MessageRepository messageRepository;

    private final ActualUser actualUser;

    private final UserRepository userRepository;

    private final OrderBoostRepository orderBoostRepository;

    private final ChangeAccountStatus changeAccountStatus;

    public MessageHandler(MessageRepository messageRepository, ActualUser actualUser, UserRepository userRepository, OrderBoostRepository orderBoostRepository,
                          ChangeAccountStatus changeAccountStatus) {
        this.messageRepository = messageRepository;
        this.actualUser = actualUser;
        this.userRepository = userRepository;
        this.orderBoostRepository = orderBoostRepository;
        this.changeAccountStatus = changeAccountStatus;
    }
/*
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
    */
    public Set<Long> setOfRecipientId(HttpServletRequest request){
        Set<Long> recipientId = new TreeSet<>();
        for (Message message : messageRepository.findAll()) {
            if (Objects.equals(message.getUser2().getId(), actualUser.getActualUser(request).getId())) {
                recipientId.add(message.getUser().getId());
            }
        }
        return recipientId;
    }
    /*
    public Set<Long> setOfSenderId(HttpServletRequest request){
        Set<Long> senderId = new TreeSet<>();
        for (Message message : messageRepository.findAll()) {
            if (Objects.equals(message.getUser().getId(), actualUser.getActualUser(request).getId())) {
                senderId.add(message.getUser2().getId());
            }
        }
        return senderId;
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
    */
    public List<User> listOfRecipients(HttpServletRequest request){
        Optional<OrderBoost> orderBoost = orderBoostRepository.findOrderBoostByUserOrBooster(actualUser.getActualUser(request));
        List<User> users = new ArrayList<>();
        if(changeAccountStatus.getCurrentUserRole(actualUser.getActualUser(request)) == RoleName.ROLE_ADMIN){
            userRepository.findAll().iterator().forEachRemaining(user -> {
                if(user.getUsername().equals(actualUser.getActualUser(request).getUsername())){
                }
                else {
                    users.add(user);
                }
            });
        }
        else if(changeAccountStatus.getCurrentUserRole(actualUser.getActualUser(request)) == RoleName.ROLE_USER) {
            users.add(userRepository.findByUsername("Admin123x"));
            orderBoost.ifPresent(boost -> users.add(boost.getBooster()));
        }
        else if(changeAccountStatus.getCurrentUserRole(actualUser.getActualUser(request)) == RoleName.ROLE_BOOSTER) {
            users.add(userRepository.findByUsername("Admin123x"));
            orderBoost.ifPresent(boost -> users.add(boost.getUser()));
        }
        return users;
    }

    public void sendMessage(Message message,HttpServletRequest request){
        Message messageDB = new Message(message.getTitle(), message.getMessage(),actualUser.getActualUser(request),message.getUser2());
        messageRepository.save(messageDB);
    }

    public List<Message> getConversation(User user,User user2){
        return messageRepository.findAllByUserAndUser2(user,user2);
    }
}