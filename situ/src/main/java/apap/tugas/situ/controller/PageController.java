package apap.tugas.situ.controller;


import apap.tugas.situ.model.RoleModel;
import apap.tugas.situ.model.UserModel;
import apap.tugas.situ.service.RoleService;
import apap.tugas.situ.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class PageController {

    @Autowired
    RoleService roleService;

    @Autowired
    UserService userService;

    @RequestMapping("/")
    public String home(Authentication authentication, Model model) {

        model.addAttribute("user", userService.getUser(authentication.getName()));
      
        return "home";
    }

    @RequestMapping("/login")
    public String login(){
        return "login";
    }

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    private String halamanAdmin(Authentication authentication, Model model){
        List<RoleModel> listRole = roleService.findAll();
        UserModel user = userService.getUser(authentication.getName());
        System.out.println("masuk method hala");
        model.addAttribute("listRole", listRole);
        model.addAttribute("user", user);
//        if(user.getRole().getRole() != null){
//            model.addAttribute("userRole", user.getRole().getRole());
//        }

        return "admin";

    }


}
