package pl.javastart.manage.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.javastart.repository.UserRepository;

@Service
public class UserManage {

    @Autowired
    private UserRepository userRepository;


    public void banAccount(Long id){
        System.out.println(" >>>>>>>>>>>>>>"+checkId(id));
        userRepository.updateEnabledStatement2(checkId(id),false);
        //get user
        //set enabled to false
        //delete user
        //save user
    }

    public void unBanAccount(Long id){
        userRepository.updateEnabledStatement2(checkId(id),true);
    }

    private Long checkId(Long id){
        String id2 = String.valueOf(id);
        String tab[] = id2.split("%");
        Long id3  = Long.parseLong(tab[0]);
        return id3;
    }
}
