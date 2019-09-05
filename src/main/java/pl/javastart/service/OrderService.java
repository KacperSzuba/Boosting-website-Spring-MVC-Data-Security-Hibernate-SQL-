package pl.javastart.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.javastart.model.entity.OrderBoost;
import pl.javastart.model.entity.User;
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
        OrderBoost orderBoost = new OrderBoost();
        User user = userRepository.findByUsername("user123");
        System.out.println(">>>>>>>>>>>>>>>>>"+user.getUsername());
        orderBoost.setDate(LocalDateTime.now());
        orderBoost.setOrderName("Boost2");
        orderBoost.setWhetherPaid(true);
        orderBoost.setUser(user);
        orderRepository.save(orderBoost);

    }

    public void whichUserBought(){
        OrderBoost orderBoost =orderRepository.findOrderBoostByOrderName("Boost");
        System.out.println(orderBoost.getUser().getUsername());
    }
}
