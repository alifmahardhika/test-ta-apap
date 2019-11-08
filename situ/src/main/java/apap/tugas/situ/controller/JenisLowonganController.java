package apap.tugas.situ.controller;

import apap.tugas.situ.model.JenisLowonganModel;
import apap.tugas.situ.service.JenisLowonganService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

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

    @RequestMapping(value = "/jenis-lowongan/delete/{idJenis}")
    public String deleteJenis(@PathVariable Long idJenis, Model model) {
        JenisLowonganModel target = jenisLowonganService.findJenisById(idJenis);
        List<JenisLowonganModel> allJenis = jenisLowonganService.findAllJenis();
        model.addAttribute("listJenis", allJenis);

        if (jenisLowonganService.checkDeletable(target)){
            model.addAttribute("namaJenis", target.getNama());
            jenisLowonganService.deleteJenis(target);
            return "jenisLowongan/delete-sukses";
        }
        else{
            model.addAttribute("namaJenis", target.getNama());
            return "error/gagal-delete-jenis-punya-relasi";
        }
    }

    @RequestMapping(value = "jenis-lowongan/view-all", method = RequestMethod.GET)
    public String viewAll(Model model) {
        List<JenisLowonganModel> allJenis = jenisLowonganService.findAllJenis();
        model.addAttribute("listJenis", allJenis);
        return "jenisLowongan/viewAll";
    }

}
