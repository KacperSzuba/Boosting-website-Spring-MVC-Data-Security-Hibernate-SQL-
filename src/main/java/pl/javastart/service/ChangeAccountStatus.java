package pl.javastart.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.javastart.repository.UserRepository;

@Service
public class ChangeAccountStatus {

    @Autowired
    private UserRepository userRepository;


    public void banAccount(Long id){
        userRepository.changeEnabledStatementQuery(id,false);
    }

    public void unBanAccount(Long id){
        userRepository.changeEnabledStatementQuery(id,true);
    }
}
