package com.BoostingWebsite.account.booster;

import com.BoostingWebsite.order.entity.OrderBoost;
import com.BoostingWebsite.order.repository.OrderBoostRepository;
import org.springframework.stereotype.Service;
import com.BoostingWebsite.account.user.ApplicationSession;
import com.BoostingWebsite.LeagueOfLegendsAPIConnector;
import com.BoostingWebsite.account.user.User;
import com.BoostingWebsite.order.Region;

import java.io.IOException;
import java.util.List;

@Service
class BoosterService {
    private String message;

    private LeagueOfLegendsAPIConnector leagueOfLegendsAPIConnector;

    private final ApplicationSession applicationSession;
    private final OrderBoostRepository orderBoostRepository;

    BoosterService(ApplicationSession applicationSession, OrderBoostRepository orderBoostRepository) {
        this.applicationSession = applicationSession;
        this.orderBoostRepository = orderBoostRepository;
    }

    List<OrderBoost> findFreeOrderBoost() {
        return orderBoostRepository.findOrderBoostByBoosterEqualsNull();
    }

    void addBoost(Long orderId) {
        leagueOfLegendsAPIConnector = new LeagueOfLegendsAPIConnector(getUsername(), getRegion());
        if (checkIfTheBoosterHasNoOrders()) {
            orderBoostRepository.findFreeOrderBoosts(orderId, loggedInBooster());
            setMessage("You correct took order");
        } else {
            setMessage("You incorrect took order");
        }
    }

    void finishBoost() throws IOException {
        if (whetherOrderIsCompleted()) {
            orderBoostRepository.setOrderAsDone(findCurrentBoost().getId());
            setMessage("You have successfully completed boosting");
        } else {
            setMessage("You have not completed boosting correctly");
        }
    }

    private boolean whetherOrderIsCompleted() throws IOException {
        return isDivisionsAreEqual() && isTiersAreEqual();
    }

    private boolean isDivisionsAreEqual() throws IOException {
        String destinationDivision = findCurrentBoost().getDestinationLeague().getDivision().toString();
        String divisionFromApi = leagueOfLegendsAPIConnector.getActualSoloDuoDivision();
        return destinationDivision.equals(divisionFromApi);
    }

    private boolean isTiersAreEqual() throws IOException {
        String destinationTier = findCurrentBoost().getDestinationLeague().getTier().toString();
        String tierFromApi = leagueOfLegendsAPIConnector.getActualSoloDuoTier();
        return destinationTier.equals(tierFromApi);
    }

    OrderBoost findCurrentBoost() {
        return orderBoostRepository.findCurrentBoost(loggedInBooster());
    }

    List<OrderBoost> listOfDoneOrderBoosts() {
        return orderBoostRepository.findDoneOrderBoost(loggedInBooster());
    }

    private boolean checkIfTheBoosterHasNoOrders() {
        return orderBoostRepository.checkIfTheBoosterHasNoOrders(loggedInBooster()).isEmpty();
    }

    private User loggedInBooster() {
        return applicationSession.getActualUser();
    }

    private String getUsername() {
        return findCurrentBoost().getAccountDetails().getLolUsername();
    }

    private Region getRegion() {
        return findCurrentBoost().getAccountDetails().getRegion();
    }

    public String getMessage() {
        return message;
    }

    private void setMessage(String message) {
        this.message = message;
    }
}
