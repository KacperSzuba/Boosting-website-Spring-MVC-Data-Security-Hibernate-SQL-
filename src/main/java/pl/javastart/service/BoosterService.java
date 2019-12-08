package pl.javastart.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.javastart.manage.ActualUser;
import pl.javastart.manage.api.LeagueOfLegendsAPIConnector;
import pl.javastart.model.entity.OrderBoost;
import pl.javastart.model.entity.enums.Region;
import pl.javastart.repository.OrderBoostRepository;
import pl.javastart.repository.UserRepository;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

@Service
public class BoosterService {
    private String message;

    @Autowired
    private ActualUser actualUser;

    @Autowired
    private OrderBoostRepository orderBoostRepository;

    @Autowired
    private UserRepository userRepository;

    private LeagueOfLegendsAPIConnector leagueOfLegendsAPIConnector;

    public List<OrderBoost> findFreeOrderBoost(){
       return orderBoostRepository.findOrderBoostByBoosterEqualsNull();
    }

    public void addBoost(Long id, HttpServletRequest request){
        if(checkIfTheBoosterHasNoOrders(request)){
            orderBoostRepository.findFreeOrderBoosts(id,actualUser.getActualUser(request));

            setMessage("You correct took order");
        }
        else {
            setMessage("You incorrect took order");
        }
    }
    public void finishBoost(HttpServletRequest request) throws IOException {
        leagueOfLegendsAPIConnector = new LeagueOfLegendsAPIConnector(getUsername(request),getRegion(request));
        if(isDivisionsAreEqual(request) && isTiersAreEqual(request)){
            orderBoostRepository.setOrderAsDone(findCurrentBoost(request).getId());
            setMessage("You have successfully completed boosting");
        }
        else {
            setMessage("You have not completed boosting correctly");
        }
    }

    private boolean isDivisionsAreEqual(HttpServletRequest request) throws IOException {
        String destinationDivision = findCurrentBoost(request).getDestinationDivision().toString();
        String divisionFromApi = leagueOfLegendsAPIConnector.getActualSoloDuoDivision();
        return destinationDivision.equals(divisionFromApi);
    }

    private boolean isTiersAreEqual(HttpServletRequest request) throws IOException {
        String destinationTier = findCurrentBoost(request).getDestinationTier().toString();
        String tierFromApi = leagueOfLegendsAPIConnector.getActualSoloDuoTier();
        return destinationTier.equals(tierFromApi);
    }

    public OrderBoost findCurrentBoost(HttpServletRequest request){
        return orderBoostRepository.findCurrentBoost(actualUser.getActualUser(request));
    }

    public List<OrderBoost> listOfDoneOrderBoosts(HttpServletRequest request){
        return orderBoostRepository.findDoneOrderBoost(actualUser.getActualUser(request));
    }

    private boolean checkIfTheBoosterHasNoOrders(HttpServletRequest request){
        return orderBoostRepository.checkIfTheBoosterHasNoOrders(actualUser.getActualUser(request)).isEmpty();
    }

    private String getUsername(HttpServletRequest request){
        return findCurrentBoost(request).getLolUsername();
    }

    private Region getRegion(HttpServletRequest request){
        return findCurrentBoost(request).getRegion();
    }

    public String getMessage() {
        return message;
    }

    private void setMessage(String message) {
        this.message = message;
    }
}
