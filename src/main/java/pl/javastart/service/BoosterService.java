package pl.javastart.service;

import org.springframework.stereotype.Service;
import pl.javastart.manage.ActualUser;
import pl.javastart.manage.api.LeagueOfLegendsAPIConnector;
import pl.javastart.model.entity.order.OrderBoost;
import pl.javastart.model.entity.user.User;
import pl.javastart.model.enums.Region;
import pl.javastart.repository.order.OrderBoostRepository;

import java.io.IOException;
import java.util.List;

@Service
public class BoosterService {
    private String message;

    private LeagueOfLegendsAPIConnector leagueOfLegendsAPIConnector;

    private final ActualUser actualUser;
    private final OrderBoostRepository orderBoostRepository;

    public BoosterService(ActualUser actualUser,OrderBoostRepository orderBoostRepository){
        this.actualUser = actualUser;
        this.orderBoostRepository = orderBoostRepository;
    }

    public List<OrderBoost> findFreeOrderBoost(){
       return orderBoostRepository.findOrderBoostByBoosterEqualsNull();
    }

    public void addBoost(Long orderId){
        leagueOfLegendsAPIConnector = new LeagueOfLegendsAPIConnector(getUsername(),getRegion());
        if(checkIfTheBoosterHasNoOrders()){
            orderBoostRepository.findFreeOrderBoosts(orderId, loggedInBooster());
            setMessage("You correct took order");
        }
        else {
            setMessage("You incorrect took order");
        }
    }

    public void finishBoost() throws IOException {
        if(whetherOrderIsCompleted()){
            orderBoostRepository.setOrderAsDone(findCurrentBoost().getId());
            setMessage("You have successfully completed boosting");
        }
        else {
            setMessage("You have not completed boosting correctly");
        }
    }

    private boolean whetherOrderIsCompleted() throws IOException {
        return isDivisionsAreEqual() && isTiersAreEqual();
    }

    private boolean isDivisionsAreEqual() throws IOException {
        String destinationDivision = findCurrentBoost().getDestinationDivision().toString();
        String divisionFromApi = leagueOfLegendsAPIConnector.getActualSoloDuoDivision();
        return destinationDivision.equals(divisionFromApi);
    }

    private boolean isTiersAreEqual() throws IOException {
        String destinationTier = findCurrentBoost().getDestinationTier().toString();
        String tierFromApi = leagueOfLegendsAPIConnector.getActualSoloDuoTier();
        return destinationTier.equals(tierFromApi);
    }

    public OrderBoost findCurrentBoost(){
        return orderBoostRepository.findCurrentBoost(loggedInBooster());
    }

    public List<OrderBoost> listOfDoneOrderBoosts(){
        return orderBoostRepository.findDoneOrderBoost(loggedInBooster());
    }

    private boolean checkIfTheBoosterHasNoOrders(){
        return orderBoostRepository.checkIfTheBoosterHasNoOrders(loggedInBooster()).isEmpty();
    }

    private User loggedInBooster(){
        return actualUser.getActualUser();
    }

    private String getUsername(){
        return findCurrentBoost().getLolUsername();
    }

    private Region getRegion(){
        return findCurrentBoost().getRegion();
    }

    public String getMessage() {
        return message;
    }

    private void setMessage(String message) {
        this.message = message;
    }
}
