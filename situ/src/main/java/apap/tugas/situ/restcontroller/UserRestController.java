package apap.tugas.situ.restcontroller;

import apap.tugas.situ.model.RoleModel;
import apap.tugas.situ.model.UserModel;
import apap.tugas.situ.rest.PegawaiDetail;
import apap.tugas.situ.service.RoleService;
import apap.tugas.situ.service.UserRestService;
import apap.tugas.situ.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserRestController {

    @Autowired
    private UserRestService userRestService;

    @Autowired
    RoleService roleService;

    @Autowired
    UserService userService;



//    @GetMapping(value = "/employees/{uuid}")
//    private UserModel retrievePegawai(@PathVariable("uuid") String uuid){
//        try {
//            return userRestService.getPegawai(uuid);
//        } catch (NoSuchElementException e){
//            throw new ResponseStatusException(
//                    HttpStatus.NOT_FOUND, "ID User " + String.valueOf(uuid) + " Not Found");
//        }
//    }

//    @RequestMapping(value = "/detailUser/{uuid}", method = RequestMethod.GET)
//    private String getUserDetail(@PathVariable String uuid, Model model) {
//        UserModel userModel = userService.getUserById(uuid).get();
//        Mono<String> userRest = userRestService.getPegawai(uuid);
//        model.addAttribute("user",userModel);
//        model.addAttribute("userRest",userRest.block());
//        System.out.println(userRest.block());
//        return "userDetail";
//    }

}
