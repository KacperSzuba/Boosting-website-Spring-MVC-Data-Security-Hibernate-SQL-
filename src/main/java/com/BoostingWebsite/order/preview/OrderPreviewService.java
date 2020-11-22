package com.BoostingWebsite.order.preview;

import com.BoostingWebsite.api.LeagueOfLegendsAPIConnector;
import com.BoostingWebsite.account.user.ApplicationSession;
import com.BoostingWebsite.account.user.User;
import com.BoostingWebsite.account.user.UserRepository;
import com.BoostingWebsite.order.Division;
import com.BoostingWebsite.order.Region;
import com.BoostingWebsite.order.Tier;
import com.BoostingWebsite.order.entity.League;
import com.BoostingWebsite.order.entity.OrderBoost;
import com.BoostingWebsite.order.repository.OrderBoostRepository;
import org.springframework.stereotype.Service;

import java.io.IOException;
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

    public League getCurrentLeague() throws OrderBoostNotFoundException, IOException {
        String username = getOrderBoost().getAccountDetails().getLolUsername();
        Region region = getOrderBoost().getAccountDetails().getRegion();

        LeagueOfLegendsAPIConnector leagueOfLegendsAPIConnector = new LeagueOfLegendsAPIConnector(username, region);

        Tier tier = Tier.valueOf(leagueOfLegendsAPIConnector.getActualSoloDuoTier());
        Division division = Division.getDivision(leagueOfLegendsAPIConnector.getActualSoloDuoDivision());
        int leaguePoints = Integer.parseInt(leagueOfLegendsAPIConnector.getActualSoloDuoLeaguePoints());

        return new League(tier, division, leaguePoints);
    }
}
