package apap.tugas.situ.controller;

import apap.tugas.situ.model.JenisSuratModel;
import apap.tugas.situ.model.PengajuanSuratModel;
import apap.tugas.situ.model.UserModel;
import apap.tugas.situ.service.JenisSuratService;
import apap.tugas.situ.service.PengajuanSuratService;
import apap.tugas.situ.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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

    @Autowired
    UserService userService;

    @RequestMapping(value = "/pengajuan-surat/add", method = RequestMethod.GET)
    public String addPengajuanSuratForm(Model model) {
        PengajuanSuratModel newPengajuanSurat = new PengajuanSuratModel();
        model.addAttribute("pengajuanSurat", newPengajuanSurat);
        model.addAttribute("listJenis", jenisSuratService.getJenisSuratList());
        return "pengajuan-surat/form-add-pengajuan-surat";
    }

    @RequestMapping(value = "/pengajuan-surat/add", method = RequestMethod.POST)
    public String addPengajuanSuratSubmit(@ModelAttribute PengajuanSuratModel pengajuanSurat, Authentication auth, Long idJenisSurat) {
        JenisSuratModel jenisSurat = jenisSuratService.getJenisSuratByIdJenisSurat(idJenisSurat).get();
        pengajuanSurat.setUser(userService.getUser(auth.getName()));
        pengajuanSurat.setJenisSurat(jenisSurat);
        pengajuanSuratService.addPengajuanSurat(pengajuanSurat);
        return "pengajuan-surat/add-pengajuan-surat";
    }

    @RequestMapping(value = "pengajuan-surat/view-all", method = RequestMethod.GET)
    public String viewPengajuanSurat(Authentication auth, Model model) {
        UserModel user = userService.getUser(auth.getName());
        if (user.getRole().getNama().equals("Guru") || user.getRole().getNama().equals("Siswa")) {
            List<PengajuanSuratModel> listPengajuanSurat = pengajuanSuratService.getPengajuanSuratListByUser(user);
            model.addAttribute("list", listPengajuanSurat);
            return "pengajuan-surat/view-pengajuan-surat";
        }
        List<PengajuanSuratModel> listPengajuanSurat = pengajuanSuratService.getPengajuanSuratList();
        if (listPengajuanSurat.isEmpty()){
            model.addAttribute("kosong", "Belum ada surat" );
        }
        model.addAttribute("list", listPengajuanSurat);
        return "pengajuan-surat/view-pengajuan-surat";
    }

    @RequestMapping(value = "/pengajuan-surat/delete/{idPengajuanSurat}", method = RequestMethod.GET)
    public String deleteJenisSurat(@PathVariable Long idPengajuanSurat,
                                   Model model) {
        List<PengajuanSuratModel> pengajuanSurat = pengajuanSuratService.getPengajuanSuratList();

        for (PengajuanSuratModel surat : pengajuanSurat){
            if(surat.getId().equals(idPengajuanSurat)){
                pengajuanSuratService.deletePengajuanSurat(surat);
                model.addAttribute("noSurat", surat.getId());
            }
        }

//        model.addAttribute("alasan", "Pengajuan Surat berhasil dihapus." );


        return "pengajuan-surat/hapus-pengajuanSurat";
    }
}
