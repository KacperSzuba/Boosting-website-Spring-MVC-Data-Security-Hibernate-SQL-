package com.BoostingWebsite.order.preview;

import com.BoostingWebsite.account.user.ApplicationSession;
import com.BoostingWebsite.account.user.User;
import com.BoostingWebsite.account.user.UserRepository;
import com.BoostingWebsite.message.Message;
import com.BoostingWebsite.message.MessageRepository;
import com.BoostingWebsite.order.entity.OrderBoost;
import com.BoostingWebsite.order.repository.OrderBoostRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderPreviewService {
    private final OrderBoostRepository orderBoostRepository;
    private final MessageRepository messageRepository;
    private final UserRepository userRepository;
    private final ApplicationSession applicationSession;

    public OrderPreviewService(OrderBoostRepository orderBoostRepository, MessageRepository messageRepository, UserRepository userRepository, ApplicationSession applicationSession) {
        this.orderBoostRepository = orderBoostRepository;
        this.messageRepository = messageRepository;
        this.userRepository = userRepository;
        this.applicationSession = applicationSession;
    }

    OrderBoost getOrderBoost() throws OrderBoostNotFoundException {
        return orderBoostRepository
                .findActiveBoost(applicationSession.getActualUser())
                .orElseThrow(OrderBoostNotFoundException::new);
    }

    synchronized void saveMessage(MessageDTO messageDTO){
        User sender = userRepository.findByUsername(messageDTO.getSenderName());
        User recipient = userRepository.findByUsername(messageDTO.getRecipientName());
        Message message = new Message(messageDTO.getContent(), sender, recipient);
        messageRepository.save(message);
    }

    public List<MessageDTO> getExistingChatMessages() throws OrderBoostNotFoundException {
        List<Message> chatMessages = messageRepository.getChatMessages(getOrderBoost().getUser().getId(), getOrderBoost().getBooster().getId());

        return ChatMessageMapper.mapMessagesToChatDTOs(chatMessages);
    }
}
