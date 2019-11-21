package apap.tugas.situ.restcontroller;

import apap.tugas.situ.model.UserModel;
import apap.tugas.situ.rest.JumlahPegawaiDetail;
import apap.tugas.situ.service.LowonganRestService;
import apap.tugas.situ.service.RoleService;
import apap.tugas.situ.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PinjamanRestController {

    @Autowired
    private LowonganRestService lowonganRestService;

    @Autowired
    RoleService roleService;

    @Autowired
    UserService userService;

    @RequestMapping(value = "/jumlah-pegawai", method = RequestMethod.GET)
    public String getJumlah(Authentication authentication, Model model){

        UserModel user = userService.getUser(authentication.getName());
        JumlahPegawaiDetail jumlahPegawaiDetail;

        if(user.getRole().getId().equals(2L)){
            jumlahPegawaiDetail = lowonganRestService.getJumlahPegawaiDetail().block();
            model.addAttribute("jumlah", jumlahPegawaiDetail);
            System.out.println("===========================================masuk getJumlah di pinjamanrestcontroller");
            System.out.println(jumlahPegawaiDetail.toString());
        }


        model.addAttribute("user", user);
        return "";
    }
}
