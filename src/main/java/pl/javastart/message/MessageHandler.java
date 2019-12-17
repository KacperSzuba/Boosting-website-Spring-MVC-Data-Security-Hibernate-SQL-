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
    private Long idOfConversation;

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

    public void sendMessage(Message message,HttpServletRequest request){
        Message messageDB = new Message(message.getTitle(), message.getMessage(),actualUser.getActualUser(request),message.getRecipientOfTheMessage());
        messageRepository.save(messageDB);
    }

    public List<User> listOfRecipients(HttpServletRequest request){
        Optional<OrderBoost> orderBoost = orderBoostRepository.findOrderBoostByUserOrBooster(actualUser.getActualUser(request));
        List<User> users = new ArrayList<>();
        if(changeAccountStatus.getCurrentUserRole(actualUser.getActualUser(request)) == RoleName.ROLE_ADMIN){
            userRepository.findAll().iterator().forEachRemaining(user -> {
                if( ! user.getUsername().equals(actualUser.getActualUser(request).getUsername())) {
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

    public Set<Long> setOfSendMessages(HttpServletRequest request){
        Set<Long> recipientId = new TreeSet<>();
        for (Message message : messageRepository.findAll()) {
            if (Objects.equals(message.getMessageSender().getId(), actualUser.getActualUser(request).getId())) {
                recipientId.add(message.getRecipientOfTheMessage().getId());
            }
            else if (Objects.equals(message.getRecipientOfTheMessage().getId(), actualUser.getActualUser(request).getId())) {
                recipientId.add(message.getMessageSender().getId());
            }
        }
        return recipientId;
    }

    public List<Message> conversationSortedByDataDESC(Long id, HttpServletRequest request){
        List<Message> list = new ArrayList<>();
        list.addAll(getMessagesReceived(id,request));
        list.addAll(getMessagesSent(request,id));
        Collections.sort(list);
        return list;
    }

    private List<Message> getMessagesReceived(Long id, HttpServletRequest request){
        return messageRepository.findAllByUserAndUser2(userRepository.findById(id).get(),actualUser.getActualUser(request));
    }

    private List<Message> getMessagesSent(HttpServletRequest request, Long id){
        return messageRepository.findAllByUserAndUser2(actualUser.getActualUser(request),userRepository.findById(id).get());
    }

    //zmienić metode getTemp
    public Long getTemp(){
        return messageRepository.findTopByOrderByIdDesc().getRecipientOfTheMessage().getId();
    }

    public void setIdOfConversation(Long idOfConversation){
        this.idOfConversation= idOfConversation;
    }

    public Long getIdOfConversation(){
        return idOfConversation;
    }
}