package pl.javastart.contoller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import pl.javastart.service.ChangeAccountStatus;
import pl.javastart.model.entity.User;
import pl.javastart.repository.UserRepository;

import java.util.List;

@RequestMapping("/admin")
@Controller
public class AdminController {

    @Autowired
    private ChangeAccountStatus changeAccountStatus;

    @Autowired
    private UserRepository userRepository;

    @RequestMapping
    public String showAdminPage(){
        return "jsp/adminPages/admin";
    }

    @RequestMapping("/ban_user")
    public ModelAndView showUserStatementPage(){
        List<User> users = (List<User>) userRepository.findAll();
        return new ModelAndView("jsp/adminPages/admin_UserStatement","user",users);
    }
    @GetMapping("/ban/{id}")
    public String ban(@PathVariable("id") final  Long id){
        changeAccountStatus.banAccount(id);
        return "redirect:/admin/ban_user";
    }
    @GetMapping("/un-ban/{id}")
    public String unban(@PathVariable("id") final Long id){
        changeAccountStatus.unBanAccount(id);
        return "redirect:/admin/ban_user";
    }
}