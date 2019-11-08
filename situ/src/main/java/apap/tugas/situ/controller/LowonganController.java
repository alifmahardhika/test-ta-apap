package apap.tugas.situ.controller;

import apap.tugas.situ.model.JenisLowonganModel;
import apap.tugas.situ.model.LowonganModel;
import apap.tugas.situ.service.JenisLowonganService;
import apap.tugas.situ.service.LowonganService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class LowonganController {
    @Qualifier("lowonganServiceImpl")
    @Autowired
    LowonganService lowonganService;

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
    public String addJenisSubmit(@ModelAttribute LowonganModel lowonganModel, Model model, @RequestParam("idJenis") String idJenisBaru) {
        lowonganModel.setIdJenis(Long.valueOf(idJenisBaru));
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
        lowonganService.changeJumlahLowongan(idLowongan, lowongan.getJumlah());
//        model.addAttribute("lowongan", lowongan);
        List<LowonganModel> allLowongan = lowonganService.findAllLowongan();
        model.addAttribute("listLowongan", allLowongan);
        return "lowongan/ubah-jumlah-lowongan";
    }

    @RequestMapping(value = "lowongan/view-all", method = RequestMethod.GET)
    public String viewAll(Model model) {
        List<LowonganModel> allLowongan = lowonganService.findAllLowongan();
        model.addAttribute("listLowongan", allLowongan);
        return "lowongan/viewAll-lowongan";
    }

}
