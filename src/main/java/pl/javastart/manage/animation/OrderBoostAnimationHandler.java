package pl.javastart.manage.animation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.javastart.model.entity.Division;
import pl.javastart.repository.DivisionRepository;

import java.util.List;
import java.util.Optional;

@Component
public class OrderBoostAnimationHandler {

    @Autowired
    private DivisionRepository divisionRepository;

    public Division getDefaultCurrentTier(){
        return getListOfTiersByPointsAndDivision().get(5);
    }

    public Division getDefaultDestinationTier(){
        return getListOfTiersByPointsAndDivision().get(4);
    }

    public Division moveImageToLeft(Division division){
        int plus24ID = 24;
        Long divID = division.getId();
        if(plus24ID+divID>144){
            return getListOfTiersById(plus24ID+divID-144).get();
        }
        else {
            return getListOfTiersById(division.getId()+24).get();

        }
    }

    public Division moveImageToRight(Division division){
        int minus24ID = 24;
        Long divID = division.getId();
        if(divID-minus24ID<0){
            return getListOfTiersById(divID-minus24ID+144).get();
        }
        else {
            return getListOfTiersById(division.getId()-24).get();

        }
    }
    public Division moveImageUp(Division division){

        if(division.getId()==1){
            return getListOfTiersById(139L).get();
        }
        else {
            return getListOfTiersById(division.getId()-6).get();
        }
    }

    public Division moveImageDown(Division division){

        if(division.getId()==139){
            return getListOfTiersById(1L).get();
        }
        else {
            return getListOfTiersById(division.getId()+6).get();
        }
    }

    private List<Division> getListOfTiersByPointsAndDivision(){
        return divisionRepository.findAllByPointsAndDivision("0-20",1);
    }

    public List<Division> getListOfTiersByPoints(){
        return divisionRepository.findAllByPoints("0-20");
    }

    private Optional<Division> getListOfTiersById(Long id){
        return divisionRepository.findById(id);
    }

}
