package pl.javastart.contoller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import pl.javastart.model.entity.User;
import pl.javastart.model.entity.enums.RoleName;
import pl.javastart.repository.UserRepository;
import pl.javastart.service.ChangeAccountStatus;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private ChangeAccountStatus changeAccountStatus;

    @Autowired
    private UserRepository userRepository;

    @RequestMapping
    public String showAdminPage(){
        return "jsp/adminView/admin";
    }

    @RequestMapping("/listOfUsers")
    public ModelAndView showUserStatementPage(){
        List<User> users = (List<User>) userRepository.findAll();
        return new ModelAndView("jsp/adminView/admin_ListOfUsers","users",users);
    }

    @GetMapping("/ban/{id}")
    public String ban(@PathVariable("id") final  Long id){
        changeAccountStatus.banUser(id);
        return "redirect:/admin/userDetails/{id}";
    }

    @GetMapping("/un-ban/{id}")
    public String unban(@PathVariable("id") final Long id){
        changeAccountStatus.unBanUser(id);
        return "redirect:/admin/userDetails/{id}";
    }

    @GetMapping("/deleteUser/{id}")
    public String deleteUser(@PathVariable("id") final Long id){
        changeAccountStatus.deleteUser(id);
        return "redirect:/admin/listOfUsers";
    }

    @GetMapping("/setAsBooster/{id}")
    public String setAsBooster(@PathVariable("id") final Long id){
        //Develop more optimized versions
        changeAccountStatus.changeTheRoleName(id,RoleName.ROLE_BOOSTER);
        return "redirect:/admin/userDetails/{id}";
    }

    @GetMapping("/setAsUser/{id}")
    public String setAsUser(@PathVariable("id") final Long id){
        changeAccountStatus.changeTheRoleName(id,RoleName.ROLE_USER);
        return "redirect:/admin/userDetails/{id}";
    }

    @GetMapping("/userDetails/{id}")
    public String showUserDetailsPage(@PathVariable("id") final Long id, Model model){
        User user = userRepository.findById(id).get();
        model.addAttribute("user",user);
        model.addAttribute("currentRole",changeAccountStatus.getCurrentUserRole(user));
        model.addAttribute("expectedRoleIsROLE_USER",RoleName.ROLE_USER);
        model.addAttribute("expectedRoleIsROLE_BOOSTER",RoleName.ROLE_BOOSTER);
        return "jsp/adminView/admin_UserDetails";
    }
}