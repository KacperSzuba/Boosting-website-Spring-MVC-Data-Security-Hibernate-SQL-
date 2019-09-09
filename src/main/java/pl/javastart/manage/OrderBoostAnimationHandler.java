package pl.javastart.manage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.javastart.model.entity.Divisions;
import pl.javastart.model.entity.enums.Division;
import pl.javastart.model.entity.enums.Tier;
import pl.javastart.repository.DivisionsRepository;

import java.util.List;
import java.util.Optional;

@Component
public class OrderBoostAnimationHandler {

    @Autowired
    private DivisionsRepository divisionsRepository;
    private int numberOfTIer;

    public Divisions getDefaultCurrentTier(){
        return getListOfTiersByPointsAndDivision().get(5);
    }

    public Divisions getDefaultdestinationTier(){
        return getListOfTiersByPointsAndDivision().get(4);
    }

    public Divisions moveImageToLeft(Divisions divisions){
        int plus24ID = 24;
        Long divID = divisions.getId();
        if(plus24ID+divID>144){
            return getListOfTiersById(plus24ID+divID-144).get();
        }
        else {
            return getListOfTiersById(divisions.getId()+24).get();

        }
    }

    public Divisions moveImageToRight(Divisions divisions){
        int minus24ID = 24;
        Long divID = divisions.getId();
        if(divID-minus24ID<0){
            return getListOfTiersById(divID-minus24ID+144).get();
        }
        else {
            return getListOfTiersById(divisions.getId()-24).get();

        }
    }
    public Divisions moveImageUp(Divisions divisions){

        if(divisions.getId()==1){
            return getListOfTiersById(139L).get();
        }
        else {
            return getListOfTiersById(divisions.getId()-6).get();
        }
    }

    public Divisions moveImageDown(Divisions divisions){

        if(divisions.getId()==139){
            return getListOfTiersById(1L).get();
        }
        else {
            return getListOfTiersById(divisions.getId()+6).get();
        }
    }

    private List<Divisions> getListOfTiersByPointsAndDivision(){
        return divisionsRepository.findAllByPointsAndDivision("0-20",1);
    }

    public List<Divisions> getListOfTiersByPoints(){
        return divisionsRepository.findAllByPoints("0-20");
    }

    private Optional<Divisions> getListOfTiersById(Long id){
        return divisionsRepository.findById(id);
    }

    public Long getFirstIdNumber(){
        return getListOfTiersByPointsAndDivision().get(0).getId();
    }

    public Long getLastIdNumber(){
        int lastIdNumber = getListOfTiersByPointsAndDivision().size()-1;
        return getListOfTiersByPointsAndDivision().get(lastIdNumber).getId();
    }

    private void setCurrentNumberTier(int numberOfTier){
        this.numberOfTIer = numberOfTier;
    }

    private int getCurrentNumberTier(){
        return numberOfTIer;
    }

}
