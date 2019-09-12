package pl.javastart.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.javastart.model.entity.Divisions;
import pl.javastart.model.entity.OrderBoost;
import pl.javastart.model.entity.User;
import pl.javastart.repository.OrderRepository;
import pl.javastart.repository.UserRepository;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.time.LocalDateTime;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    public void makeOrder(OrderBoost orderBoost, HttpServletRequest request){
        Divisions currentDivision = (Divisions) request.getSession().getAttribute("currentTierClass");
        Divisions destinationDivision = (Divisions) request.getSession().getAttribute("destinationTierClass");
        Principal principal = request.getUserPrincipal();
        User user = userRepository.findByUsername(principal.getName());
        orderBoost.setCurrentTier(currentDivision.getTier());
        orderBoost.setCurrentDivision(currentDivision.getDivision());
        orderBoost.setDestinationTier(destinationDivision.getTier());
        orderBoost.setDestinationDivision(destinationDivision.getDivision());
        orderBoost.setDate(LocalDateTime.now());
        orderBoost.setWhetherPaid(false);
        orderBoost.setUser(user);
        orderRepository.save(orderBoost);
    }

    public void whichUserBought(){
        OrderBoost orderBoost =orderRepository.findOrderBoostBylolUsername("Boost");
        System.out.println(orderBoost.getUser().getUsername());
    }
}
