package pl.javastart.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.javastart.model.entity.OrderBoost;
import pl.javastart.model.entity.User;
import pl.javastart.repository.OrderBoostRepository;
import pl.javastart.repository.UserRepository;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.List;

@Service
public class BoosterService {
    private String message;
    @Autowired
    private OrderBoostRepository orderBoostRepository;

    @Autowired
    private UserRepository userRepository;

    public List<OrderBoost> findFreeOrderBoost(){
       return orderBoostRepository.findOrderBoostByBoosterEqualsNull();
    }

    public void addBoost(Long id, HttpServletRequest request){
        if(checkIfTheBoosterHasNoOrders(request)){
            orderBoostRepository.findFreeOrderBoosts(id,booster(request));
            setMessage("You correct took order");
        }
        else {
            setMessage("You incorrect took order");
        }
    }

    public List<OrderBoost> findCurrentBoost(HttpServletRequest request){
        return orderBoostRepository.findCurrentBoost(booster(request));
    }

    public List<OrderBoost> listOfDoneOrderBoosts(HttpServletRequest request){
        return orderBoostRepository.findDoneOrderBoost(booster(request));
    }

    private boolean checkIfTheBoosterHasNoOrders(HttpServletRequest request){
        return orderBoostRepository.checkIfTheBoosterHasNoOrders(booster(request)).isEmpty();
    }

    private User booster(HttpServletRequest request){
        Principal principal = request.getUserPrincipal();
        return userRepository.findByUsername(principal.getName());
    }

    public String getMessage() {
        return message;
    }

    private void setMessage(String message) {
        this.message = message;
    }
}
