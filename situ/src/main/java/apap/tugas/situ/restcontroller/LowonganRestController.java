package apap.tugas.situ.restcontroller;

import apap.tugas.situ.model.LowonganModel;
import apap.tugas.situ.model.UserModel;
import apap.tugas.situ.rest.JumlahPegawaiDetail;
import apap.tugas.situ.rest.JumlahPegawaiDetailResponse;
import apap.tugas.situ.service.*;
import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api")
public class LowonganRestController {

    @Autowired
    private LowonganRestService lowonganRestService;

    @Autowired
    private JenisLowonganService jenisLowonganService;

    @Autowired
    private LowonganService lowonganService;

    @Autowired
    RoleService roleService;

    @Autowired
    UserService userService;

    @RequestMapping(value = "/jumlah-pegawai", method = RequestMethod.GET)
    public ModelAndView autoCheckAndAdd(Authentication authentication, Model model){

        UserModel user = userService.getUser(authentication.getName());
        JumlahPegawaiDetailResponse jumlahPegawaiDetailResponse;

        jumlahPegawaiDetailResponse = lowonganRestService.getJumlahPegawaiDetail();
        int jumlah = lowonganRestService.getIntJumlah(jumlahPegawaiDetailResponse);
        System.out.println("masuk controller bagian getjumlah");
        if (jumlah < 5){
            String closeDate = "";
            LocalDate dateNow = LocalDate.now();
            String[] dateStr = dateNow.toString().split("-");

            //cek bulan trus bikin close date
            if (Integer.parseInt(dateStr[1]) < 12){

                int monthPlusOne = Integer.parseInt(dateStr[1]) + 1;
                dateStr[1] = "" + monthPlusOne;
                closeDate = "" + dateStr[0] + "-" +dateStr[1] + "-" + dateStr[2];
            }
            else{   //dari desember satu bulan = januari
                closeDate = "" + dateStr[0] + "-" +1 + "-" + dateStr[2];
            }
            LocalDate closingDate = LocalDate.parse(closeDate);
            Long idJenisPustakawan = jenisLowonganService.findIdByNama("Pustakawan");
            LowonganModel automatedCreation = new LowonganModel("Lowongan Pustakawan", dateNow, closingDate, "Dibutuhkan Pustakawan Cakap", 5-jumlah, idJenisPustakawan );

            if (lowonganService.foundSimilar(automatedCreation)){
                System.out.println("=====================udah ada lowongan dengan jumlah dan judul dan jenis dan tanggal yg sama");
                model.addAttribute("judulLowongan", automatedCreation.getJudul());
                ModelAndView modelAndView = new ModelAndView();
                modelAndView.setViewName("lowongan/add-fail-similar");
                return modelAndView;
            }
            else{
                lowonganService.addLowongan(automatedCreation);
                model.addAttribute("judulLowongan", automatedCreation.getJudul());
                ModelAndView modelAndView = new ModelAndView();
                modelAndView.setViewName("lowongan/add-lowongan");
                return modelAndView;
            }
        }
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("lowongan/checked-full");
        return modelAndView;
    }

}
