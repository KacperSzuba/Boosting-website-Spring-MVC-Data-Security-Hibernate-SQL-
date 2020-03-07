package com.BoostingWebsite.message;

import org.springframework.stereotype.Service;
import com.BoostingWebsite.account.user.ActualUser;
import com.BoostingWebsite.order.OrderBoost;
import com.BoostingWebsite.account.user.User;
import com.BoostingWebsite.account.roles.RoleName;
import com.BoostingWebsite.order.OrderBoostRepository;
import com.BoostingWebsite.account.user.UserRepository;
import com.BoostingWebsite.account.admin.ChangeAccountStatus;

import java.util.*;

@Service
class MessageHandler {
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

    void sendMessage(Message message){
        Message messageDB = new Message(message.getTitle(), message.getMessage(),actualUser.getActualUser(),message.getUser2());
        messageRepository.save(messageDB);
    }

    List<User> listOfRecipients(){
        Optional<OrderBoost> orderBoost = orderBoostRepository.findOrderBoostByUserOrBooster(actualUser.getActualUser());
        List<User> users = new ArrayList<>();
        if(changeAccountStatus.getCurrentUserRole(actualUser.getActualUser()) == RoleName.ROLE_ADMIN){
            userRepository.findAll().iterator().forEachRemaining(user -> {
                if( ! user.getUsername().equals(actualUser.getActualUser().getUsername())) {
                    users.add(user);
                }
            });
        }
        else if(changeAccountStatus.getCurrentUserRole(actualUser.getActualUser()) == RoleName.ROLE_USER) {
            users.add(userRepository.findByUsername("Admin123x"));
            orderBoost.ifPresent(boost -> users.add(boost.getBooster()));
        }
        else if(changeAccountStatus.getCurrentUserRole(actualUser.getActualUser()) == RoleName.ROLE_BOOSTER) {
            users.add(userRepository.findByUsername("Admin123x"));
            orderBoost.ifPresent(boost -> users.add(boost.getUser()));
        }
        return users;
    }

    public Set<Long> setOfSendMessages(){
        Set<Long> recipientId = new TreeSet<>();
        for (Message message : messageRepository.findAll()) {
            if (Objects.equals(message.getUser().getId(), actualUser.getActualUser().getId())) {
                recipientId.add(message.getUser2().getId());
            }
            else if (Objects.equals(message.getUser2().getId(), actualUser.getActualUser().getId())) {
                recipientId.add(message.getUser().getId());
            }
        }
        return recipientId;
    }

    List<User> setOfSendRecipients(){
        Set<Long> recipientId = new HashSet<>();
        for (Message message : messageRepository.findAll()) {
            if (Objects.equals(message.getUser().getId(), actualUser.getActualUser().getId())) {
                recipientId.add(message.getUser2().getId());
            }
            else if (Objects.equals(message.getUser2().getId(), actualUser.getActualUser().getId())) {
                recipientId.add(message.getUser().getId());
            }
        }
        List<User> users = (List<User>) userRepository.findAllById(recipientId);
        return users;
    }

    List<Message> conversationSortedByDataDESC(Long id){
        List<Message> list = new ArrayList<>();
        list.addAll(getMessagesReceived(id));
        list.addAll(getMessagesSent(id));
        Collections.sort(list);
        return list;
    }

    private List<Message> getMessagesReceived(Long id){
        List<Message> list = new ArrayList<>();
        try{
            list = messageRepository.findAllByUserAndUser2(userRepository.findById(id).get(),actualUser.getActualUser());;
            return list;
        }
        catch (IllegalStateException exception){
            exception.printStackTrace();
        }
        catch (Exception exception){
            exception.printStackTrace();
        }
        return list;
    }

    private List<Message> getMessagesSent(Long id){
        return messageRepository.findAllByUserAndUser2(actualUser.getActualUser(),userRepository.findById(id).get());
    }

    //zmienić metode getTemp
    Long getTemp(){
        return messageRepository.findTopByOrderByIdDesc().getUser2().getId();
    }

    void setIdOfConversation(Long idOfConversation){
        this.idOfConversation= idOfConversation;
    }

    Long getIdOfConversation(){
        return idOfConversation;
    }
}