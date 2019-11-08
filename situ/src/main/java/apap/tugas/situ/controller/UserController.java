package apap.tutorial.gopud.controller;

import apap.tutorial.gopud.model.UserModel;
import apap.tutorial.gopud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

//    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
//    private String addUserSubmit(@ModelAttribute UserModel user){
//        userService.addUser(user);
//        return "home";
//    }

    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    private String addUserSubmit(@ModelAttribute UserModel user, Model model) {
        if (user.getPassword().length() < 8) {
            model.addAttribute("pesan", "Password tidak boleh kurang dari 8 karakter!");
            return "home";
        } else {
            if (user.getPassword().matches(".*[a-zA-Z].*") && user.getPassword().matches(".*[0-9].*")) {
                userService.addUser(user);
                model.addAttribute("pesan", "Berhasil menambahkan user!");
                return "home";
            } else {
                model.addAttribute("pesan", "Password harus memiliki angka dan huruf");
                return "home";
            }
        }
    }

    @RequestMapping(value = "/update-password/{username}", method = RequestMethod.GET)
    private String updatePassword(@PathVariable(value="username") String username, Model model) {
        UserModel user = userService.getUser(username);
        model.addAttribute("user", user);
        model.addAttribute("username", username);
        model.addAttribute("pesan", "");
        return "form-update-password";
    }

    @RequestMapping(value = "/update-password/{username}", method = RequestMethod.POST)
    private String updatePasswordSubmit(@PathVariable(value="username") String username, String oldpass, String newpass, String newconfirmedpass, Model model) {
        UserModel user = userService.getUser(username);

        if (newpass.matches(".*[a-zA-Z].*") && newpass.matches(".*[0-9].*") && newpass.length() >= 8) {
            if (newpass.equals(newconfirmedpass) == false) {
                model.addAttribute("pesan", "Konfirmasi password tidak sama!");
                return "form-update-password";
            } else if (newpass.equals(oldpass)){
                model.addAttribute("pesan", "Password lama dan password baru sama!");
                return "form-update-password";
            }else {
                boolean valid = userService.validatePassword(user.getPassword(), oldpass);
                if (valid == true) {
                    userService.updatePassword(username, newpass);
                    return "update-password-success";
                } else {
                    model.addAttribute("pesan", "Password kurang sesuai!");
                    return "form-update-password";
                }
            }
        } else {
            model.addAttribute("pesan", "Password tidak boleh kurang dari 8 karakter dan harus mengandung huruf dan angka!");
            return "form-update-password";
        }

    }
}
