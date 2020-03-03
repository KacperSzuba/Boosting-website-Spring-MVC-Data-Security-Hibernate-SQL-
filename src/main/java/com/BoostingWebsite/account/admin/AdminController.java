package com.BoostingWebsite.account.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.BoostingWebsite.order.OrderBoost;
import com.BoostingWebsite.account.user.User;
import com.BoostingWebsite.account.roles.RoleName;
import com.BoostingWebsite.order.OrderBoostRepository;
import com.BoostingWebsite.account.user.UserRepository;
import com.BoostingWebsite.account.admin.ChangeAccountStatus;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final ChangeAccountStatus changeAccountStatus;
    private final UserRepository userRepository;
    private final OrderBoostRepository orderBoostRepository;

    public AdminController(ChangeAccountStatus changeAccountStatus, UserRepository userRepository, OrderBoostRepository orderBoostRepository) {
        this.changeAccountStatus = changeAccountStatus;
        this.userRepository = userRepository;
        this.orderBoostRepository = orderBoostRepository;
    }

    @GetMapping
    public String showAdminPage(){
        return "adminView/admin";
    }

    @GetMapping("/listOfUsers")
    public ModelAndView showUserStatementPage(){
        List<User> users = (List<User>) userRepository.findAll();
        return new ModelAndView("adminView/admin_ListOfUsers","users",users);
    }

    @GetMapping("/listOfOrders")
    public ModelAndView showListOfOrders(){
        List<OrderBoost> orderBoostList = (List<OrderBoost>) orderBoostRepository.findAll();
        return new ModelAndView("adminView/admin_ListOfOrders","orders",orderBoostList);
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
        return "adminView/admin_UserDetails";
    }
}
