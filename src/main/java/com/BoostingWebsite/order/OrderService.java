package com.BoostingWebsite.order;

import com.BoostingWebsite.order.division.repository.DivisionRepository;
import org.springframework.stereotype.Service;
import com.BoostingWebsite.account.user.ActualUser;
import com.BoostingWebsite.order.division.entity.TierImage;
import com.BoostingWebsite.order.division.repository.TierImageRepository;
import com.BoostingWebsite.order.division.repository.TierRepository;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.TreeMap;

@Service
class OrderService {

    private final ActualUser actualUser;
    private final OrderBoostRepository orderBoostRepository;
    private final TierImageRepository tierImageRepository;
    private final TierRepository tierRepository;
    private final DivisionRepository divisionsRepository;
    private HttpServletRequest request;


    public OrderService(ActualUser actualUser, OrderBoostRepository orderBoostRepository, TierImageRepository tierImageRepository, TierRepository tierRepository,
                        DivisionRepository divisionsRepository, HttpServletRequest request) {
        this.actualUser = actualUser;
        this.orderBoostRepository = orderBoostRepository;
        this.tierImageRepository = tierImageRepository;
        this.tierRepository = tierRepository;
        this.divisionsRepository = divisionsRepository;
        this.request = request;
    }

    void makeOrder(OrderBoost orderBoost){
        TierImage currentLeague = cookies().get("currentTierImageSource");
        TierImage destinationLeague = cookies().get("destinationTierImageSource");
        orderBoost.setCurrentTier(tierRepository.findById(currentLeague.getTier().getId()).get().getTier());
        orderBoost.setCurrentDivision(divisionsRepository.findById(currentLeague.getDivision().getId()).get().getDivision());
        orderBoost.setDestinationTier(tierRepository.findById(destinationLeague.getTier().getId()).get().getTier());
        orderBoost.setDestinationDivision(divisionsRepository.findById(destinationLeague.getDivision().getId()).get().getDivision());
        orderBoost.setDate(LocalDateTime.now());
        orderBoost.setWhetherPaid(false);
        orderBoost.setUser(actualUser.getActualUser());
        orderBoostRepository.save(orderBoost);
    }

    private Map<String,TierImage> cookies(){
        Map<String, TierImage> cookieList = new TreeMap<>();
        Cookie[] cookies = this.request.getCookies();
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