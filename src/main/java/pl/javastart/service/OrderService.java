package pl.javastart.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.javastart.model.entity.OrderBoost;
import pl.javastart.model.entity.User;
import pl.javastart.model.entity.enums.Tier;
import pl.javastart.repository.OrderRepository;
import pl.javastart.repository.UserRepository;

import java.time.LocalDateTime;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    public void makeOrder(){
        User user = userRepository.findByUsername("user123");
        OrderBoost orderBoost = new OrderBoost();
        orderBoost.setUser(user);
        orderBoost.setWhetherPaid(true);
        orderBoost.setDate(LocalDateTime.now());
        orderBoost.setCurrentDivision(1);
        orderBoost.setCurrentTier(Tier.PLATINUM);
        orderBoost.setDestinationDivision(4);
        orderBoost.setDestinationTier(Tier.DIAMOND);
        orderBoost.setLolPassword("voler");
        orderBoost.setLolUsername("voler");
        orderBoost.setSummonerID("voler");
        orderBoost.setRegion("EUW");
        orderBoost.setNoteToBoosters("Brak");
        orderRepository.save(orderBoost);
    }

    public void whichUserBought(){
        OrderBoost orderBoost =orderRepository.findOrderBoostBylolUsername("Boost");
        System.out.println(orderBoost.getUser().getUsername());
    }
}
