package apap.tugas.situ.controller;

import apap.tugas.situ.model.JenisLowonganModel;
import apap.tugas.situ.service.JenisLowonganService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class JenisLowonganController {

    @Qualifier("jenisLowonganServiceImpl")
    @Autowired
    JenisLowonganService jenisLowonganService;

    @RequestMapping(value = "/jenis-lowongan/add", method = RequestMethod.GET)
    public String addJenisFormPage(Model model) {
        JenisLowonganModel newJenis = new JenisLowonganModel();
        model.addAttribute("jenisLowongan", newJenis);
        return "jenisLowongan/form-add-jenisLowongan";
    }

    @RequestMapping(path = "/jenis-lowongan/add", method = RequestMethod.POST)
    public String addJenisSubmit(@ModelAttribute JenisLowonganModel jenisLowonganModel, Model model) {

        if(jenisLowonganService.checkValid(jenisLowonganModel.getNama())){
            jenisLowonganService.addJenisLowongan(jenisLowonganModel);
            model.addAttribute("namaJenis", jenisLowonganModel.getNama());
            return "jenisLowongan/add-jenisLowongan";
        }

        model.addAttribute("namaJenis", jenisLowonganModel.getNama());
        return "add-jenis-lowongan-fail";
    }

}
