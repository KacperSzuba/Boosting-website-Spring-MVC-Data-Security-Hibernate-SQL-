package pl.javastart.contoller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import pl.javastart.manage.user.UserManage;
import pl.javastart.model.entity.User;
import pl.javastart.repository.UserRepository;

import java.util.List;

@RequestMapping("/admin")
@Controller
public class AdminController {

    @Autowired
    private UserManage userManage;

    @Autowired
    private UserRepository userRepository;

    @RequestMapping
    public String showAdminPage(){
        return "jsp/admin";
    }

    @RequestMapping("/ban_user")
    public ModelAndView banUser(){
        List<User> users = (List<User>) userRepository.findAll();
        return new ModelAndView("jsp/admin_UserStatement","user",users);
    }
    @GetMapping("/ban/{id}")
    public String ban(@PathVariable("id") final  Long id){
        //User user = userRepository.findByUsername(username);
        System.out.println("BAN >>>>>>>>>>>>>>>>>>> "+id);
        userManage.banAccount(id);
        return "redirect:/admin/ban_user";
    }
    @GetMapping("/un-ban/{id}")
    public String unban(@PathVariable("id") final Long id){
        //User user = userRepository.findByUsername(id);
        System.out.println("UN-BAN >>>>>>>>>>>>>>>>>>> "+id);
        userManage.unBanAccount(id);
        return "redirect:/admin/ban_user";
    }
}
