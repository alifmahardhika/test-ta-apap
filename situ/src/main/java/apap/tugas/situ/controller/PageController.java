package apap.tutorial.gopud.controller;

import apap.tutorial.gopud.model.RoleModel;
import apap.tutorial.gopud.service.RoleService;
import apap.tutorial.gopud.service.UserService;
import org.apache.catalina.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;

@Controller
public class PageController {

    @Autowired
    RoleService roleService;

    @Autowired
    UserService userService;

    @RequestMapping("/")
    public String home(Authentication authentication, Model model) {
        List<RoleModel> listRole = roleService.findAll();
        model.addAttribute("listRole", listRole);
        model.addAttribute("user", userService.getUser(authentication.getName()));
        return "home";
    }

    @RequestMapping("/login")
    public String login(){
        return "login";
    }


}
