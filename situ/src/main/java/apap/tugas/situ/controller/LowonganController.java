package apap.tugas.situ.controller;

import apap.tugas.situ.model.JenisLowonganModel;
import apap.tugas.situ.model.LowonganModel;
import apap.tugas.situ.service.JenisLowonganService;
import apap.tugas.situ.service.LowonganService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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
        return "Lowongan/form-add-lowongan";
    }

    @RequestMapping(path = "/lowongan/add", method = RequestMethod.POST)
    public String addJenisSubmit(@ModelAttribute LowonganModel lowonganModel, Model model, @RequestParam("idJenis") String idJenisBaru) {
        lowonganModel.setIdJenisLowongan(Long.valueOf(idJenisBaru));
        lowonganService.addLowongan(lowonganModel);
        model.addAttribute("judulLowongan", lowonganModel.getJudul());
        return "lowongan/add-lowongan";
    }
}
