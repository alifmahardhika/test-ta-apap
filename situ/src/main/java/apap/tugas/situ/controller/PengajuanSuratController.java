package apap.tugas.situ.controller;

import apap.tugas.situ.model.JenisSuratModel;
import apap.tugas.situ.model.PengajuanSuratModel;
import apap.tugas.situ.service.JenisSuratService;
import apap.tugas.situ.service.PengajuanSuratService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class PengajuanSuratController {
    @Qualifier("pengajuanSuratServiceImpl")
    @Autowired
    PengajuanSuratService pengajuanSuratService;

    @Autowired
    JenisSuratService jenisSuratService;

    @RequestMapping(value = "/pengajuan-surat/add", method = RequestMethod.GET)
    public String addPengajuanSuratForm(Model model) {
        PengajuanSuratModel newPengajuanSurat = new PengajuanSuratModel();

        model.addAttribute("pengajuanSurat", newPengajuanSurat);
        model.addAttribute("listJenis", jenisSuratService.getJenisSuratList());
        return "pengajuan-surat/form-add-pengajuan-surat";
    }

    @RequestMapping(value = "/pengajuan-surat/add", method = RequestMethod.POST)
    public String addPengajuanSuratSubmit(@ModelAttribute PengajuanSuratModel pengajuanSurat, Long idJenisSurat) {
        JenisSuratModel jenisSurat = jenisSuratService.getJenisSuratByIdJenisSurat(idJenisSurat).get();

        pengajuanSurat.setJenisSurat(jenisSurat);
        pengajuanSuratService.addPengajuanSurat(pengajuanSurat);
        return "pengajuan-surat/add-pengajuan-surat";
    }

    @RequestMapping(value = "pengajuan-surat/view-all", method = RequestMethod.GET)
    public String viewPengajuanSurat(Model model) {
        List<PengajuanSuratModel> listPengajuanSurat = pengajuanSuratService.getPengajuanSuratList();
        model.addAttribute("list", listPengajuanSurat);
        return "pengajuan-surat/view-pengajuan-surat";
    }
}
