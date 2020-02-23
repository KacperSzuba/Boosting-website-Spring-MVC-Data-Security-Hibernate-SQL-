package pl.javastart.service;

import org.springframework.stereotype.Service;
import pl.javastart.manage.ActualUser;
import pl.javastart.model.entity.TierImage;
import pl.javastart.model.entity.order.OrderBoost;
import pl.javastart.repository.DivisionsRepository;
import pl.javastart.repository.TierImageRepository;
import pl.javastart.repository.TierRepository;
import pl.javastart.repository.order.OrderBoostRepository;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.TreeMap;

@Service
public class OrderService {

    private final ActualUser actualUser;

    private final OrderBoostRepository orderBoostRepository;

    private final TierImageRepository tierImageRepository;

    private final TierRepository tierRepository;

    private final DivisionsRepository divisionsRepository;

    public OrderService(ActualUser actualUser, OrderBoostRepository orderBoostRepository, TierImageRepository tierImageRepository, TierRepository tierRepository, DivisionsRepository divisionsRepository) {
        this.actualUser = actualUser;
        this.orderBoostRepository = orderBoostRepository;
        this.tierImageRepository = tierImageRepository;
        this.tierRepository = tierRepository;
        this.divisionsRepository = divisionsRepository;
    }

    public void makeOrder(OrderBoost orderBoost, HttpServletRequest request){
        TierImage currentLeague = cookies(request).get("currentTierImageSource");
        TierImage destinationLeague = cookies(request).get("destinationTierImageSource");
        orderBoost.setCurrentTier(tierRepository.findById(currentLeague.getTier().getId()).get().getTier());
        orderBoost.setCurrentDivision(divisionsRepository.findById(currentLeague.getDivision().getId()).get().getDivision());
        orderBoost.setDestinationTier(tierRepository.findById(destinationLeague.getTier().getId()).get().getTier());
        orderBoost.setDestinationDivision(divisionsRepository.findById(destinationLeague.getDivision().getId()).get().getDivision());
        orderBoost.setDate(LocalDateTime.now());
        orderBoost.setWhetherPaid(false);
        orderBoost.setUser(actualUser.getActualUser(request));
        orderBoostRepository.save(orderBoost);
    }

    private Map<String,TierImage> cookies(HttpServletRequest request){
        Map<String, TierImage> cookieList = new TreeMap<>();
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("currentTierImageSource")) {
                    cookieList.put("currentTierImageSource",tierImageRepository.findByImgSource(cookie.getValue()));
                }
                else if(cookie.getName().equals("destinationTierImageSource")){
                    cookieList.put("destinationTierImageSource",tierImageRepository.findByImgSource(cookie.getValue()));
                }
            }
        }
        return cookieList;
    }
}