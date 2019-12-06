package apap.tugas.situ.controller;

import apap.tugas.situ.model.JenisLowonganModel;
import apap.tugas.situ.model.LowonganModel;
import apap.tugas.situ.model.UserModel;
import apap.tugas.situ.rest.JumlahPegawaiDetailResponse;
import apap.tugas.situ.service.JenisLowonganService;
import apap.tugas.situ.service.LowonganRestService;
import apap.tugas.situ.service.LowonganService;
import apap.tugas.situ.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
public class LowonganController {
    @Qualifier("lowonganServiceImpl")
    @Autowired
    LowonganService lowonganService;

    @Autowired
    private LowonganRestService lowonganRestService;

    @Autowired
    private UserService userService;


    @Autowired
    JenisLowonganService jenisLowonganService;

    @RequestMapping(value = "/lowongan/add", method = RequestMethod.GET)
    public String addJenisFormPage(Model model) {
        LowonganModel newLowongan = new LowonganModel();
        List<JenisLowonganModel> listJenis = jenisLowonganService.findAllJenis();

        model.addAttribute("lowongan", newLowongan);
        model.addAttribute("listJenis", listJenis);
        return "lowongan/form-add-lowongan";
    }

    @RequestMapping(path = "/lowongan/add", method = RequestMethod.POST)
    public String addJenisSubmit(Authentication authentication, @ModelAttribute LowonganModel lowonganModel, Model model, @RequestParam("idJenis") String idJenisBaru) {
        lowonganModel.setIdJenis(Long.valueOf(idJenisBaru));
        UserModel user = userService.getUser(authentication.getName());

        lowonganModel.setUuidUser(user.getId());
        lowonganService.addLowongan(lowonganModel);
        model.addAttribute("judulLowongan", lowonganModel.getJudul());
        return "lowongan/add-lowongan";
    }


    @RequestMapping(value = "/lowongan/ubah-jumlah/{idLowongan}", method = RequestMethod.GET)
    public String changeLowongan(@PathVariable Long idLowongan, Model model) {
        System.out.println("=========================================");
        System.out.println(idLowongan);
        LowonganModel target = lowonganService.findLowonganById(idLowongan);
        model.addAttribute("lowongan", target);
        model.addAttribute("idUpdated", idLowongan);
        return "lowongan/form-ubah-jumlah-lowongan";
    }

    @RequestMapping(value = "/lowongan/ubah-jumlah/{idLowongan}", method = RequestMethod.POST)
    public String changeRestoranFormSubmit(@PathVariable Long idLowongan, @ModelAttribute LowonganModel lowongan,
                                           Model model) {
        LocalDate tanggalBuka = lowongan.getTanggalDibuka();
//        String[] tglBukaList = tanggalBuka.toString().split("-");

        LocalDate dateNow = LocalDate.now();
//        String[] dateStr = dateNow.toString().split("-");

        List<LowonganModel> allLowongan = lowonganService.findAllLowongan();


        if (dateNow.compareTo(tanggalBuka) > 0 || dateNow.compareTo(tanggalBuka) == 0){
            System.out.println("failed abka");
            lowonganService.changeJumlahLowongan(idLowongan, lowongan.getJumlah());
//        model.addAttribute("lowongan", lowongan);
            model.addAttribute("listLowongan", allLowongan);
            return "lowongan/ubah-jumlah-lowongan-failed";
        }
        System.out.println("babibubebibeioabduaeodbadbaoubdabambanggggg berhasil abka");
        model.addAttribute("listLowongan", allLowongan);
        return "lowongan/ubah-jumlah-lowongan";
    }

    @RequestMapping(value = "lowongan/view-all", method = RequestMethod.GET)
    public String viewAll(Model model, Authentication authentication) {
        List<LowonganModel> allLowongan = lowonganService.findAllLowongan();
        model.addAttribute("listLowongan", allLowongan);
        UserModel user = userService.getUser(authentication.getName());
        model.addAttribute("user", user);
        return "lowongan/viewAll-lowongan";
    }

    @RequestMapping(value = "api/lowongan/sinkronisasi", method = RequestMethod.GET)
    public String sinkronisasi(Model model) {
        JumlahPegawaiDetailResponse jumlahPegawaiDetailResponse;
        jumlahPegawaiDetailResponse = lowonganRestService.getJumlahPegawaiDetail();
        int jumlah = lowonganRestService.getIntJumlah(jumlahPegawaiDetailResponse);
        model.addAttribute("jumlah", jumlah);
        return "lowongan/sinkronisasi";
    }

}
