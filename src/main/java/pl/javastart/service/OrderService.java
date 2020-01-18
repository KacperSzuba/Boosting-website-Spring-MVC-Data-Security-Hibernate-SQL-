package pl.javastart.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.javastart.manage.ActualUser;
import pl.javastart.model.entity.Division;
import pl.javastart.model.entity.order.OrderBoost;
import pl.javastart.repository.order.OrderBoostRepository;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@Service
public class OrderService {

    @Autowired
    private OrderBoostRepository orderBoostRepository;

    @Autowired
    private ActualUser actualUser;

    public void makeOrder(OrderBoost orderBoost, HttpServletRequest request){
        Division currentDivision = (Division) request.getSession().getAttribute("currentTierClass");
        Division destinationDivision = (Division) request.getSession().getAttribute("destinationTierClass");
        orderBoost.setCurrentTier(currentDivision.getTier());
        orderBoost.setCurrentDivision(currentDivision.getDivision());
        orderBoost.setDestinationTier(destinationDivision.getTier());
        orderBoost.setDestinationDivision(destinationDivision.getDivision());
        orderBoost.setDate(LocalDateTime.now());
        orderBoost.setWhetherPaid(false);
        orderBoost.setUser(actualUser.getActualUser(request));
        orderBoostRepository.save(orderBoost);
    }
}
