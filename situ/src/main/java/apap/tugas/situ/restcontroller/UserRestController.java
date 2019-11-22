package apap.tugas.situ.restcontroller;

import apap.tugas.situ.model.RoleModel;
import apap.tugas.situ.model.UserModel;
import apap.tugas.situ.rest.GuruDetail;
import apap.tugas.situ.rest.PegawaiDetail;
import apap.tugas.situ.rest.SiswaDetail;
import apap.tugas.situ.service.RoleService;
import apap.tugas.situ.service.UserRestService;
import apap.tugas.situ.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.expression.ParseException;
import org.springframework.http.HttpStatus;

import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
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


    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public String addUserSubmit(@ModelAttribute UserModel user,
    		@RequestParam(required=false) String nama,
    		@RequestParam(required=false) String tempatLahir, 
    		@RequestParam(required=false) String tanggalLahir, 
    		@RequestParam(required=false) String alamat,
    		@RequestParam(required=false) String telepon, Model model) throws ParseException, java.text.ParseException {
    	
    	if (userService.getUser(user.getUsername()) != null) {
    		return "error";
    	} else {
    		userService.addUser(user);
    		Date birthDate = null;
    		if (tanggalLahir != null) {
    			//birthDate = new SimpleDateFormat("yyyy-mm-dd").parse(tanggalLahir);
    			if (user.getRole().getNama().equals("Guru")) {
    				GuruDetail guru = new GuruDetail();
    				String nig = userRestService.generateKodeNIG(tanggalLahir, user.getId());
    				DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
    				Date dateTanggal = dateFormat.parse(tanggalLahir);
    				guru.setNama(nama);
    				guru.setTempatLahir(tempatLahir);
    				guru.setTanggalLahir(dateTanggal);
    				guru.setAlamat(alamat);
    				guru.setTelepon(telepon);
    				guru.setNig(nig);
    				guru.setIdUser(user.getId());
    				if (userRestService.addGuru(user, guru).block().getStatus()=="200") {
    					return "success";
    				}
    			}
    			
    			if (user.getRole().getNama().equals("Siswa")) {
    				SiswaDetail siswa = new SiswaDetail();
    				String nis = userRestService.generateKodeNIS(tanggalLahir, user.getId());
    				DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
    				Date dateTanggal = dateFormat.parse(tanggalLahir);
    				siswa.setNama(nama);
    				siswa.setTempatLahir(tempatLahir);
    				siswa.setTanggalLahir(dateTanggal);
    				siswa.setAlamat(alamat);
    				siswa.setTelepon(telepon);
    				siswa.setNis(nis);
    				siswa.setIdUser(user.getId());
    				if (userRestService.addSiswa(user, siswa).block().getStatus()=="200") {
    					return "success";
    				}
    			}
    			
    			if (user.getRole().getNama().equals("Pegawai")) {
    				PegawaiDetail pegawai = new PegawaiDetail();
    				String nip = userRestService.generateKodeNIP(tanggalLahir, user.getId());
    				DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
    				Date dateTanggal = dateFormat.parse(tanggalLahir);
    				pegawai.setNama(nama);
    				pegawai.setTempatLahir(tempatLahir);
    				pegawai.setTanggalLahir(dateTanggal);
    				pegawai.setAlamat(alamat);
    				pegawai.setTelepon(telepon);
    				pegawai.setNip(nip);
    				pegawai.setIdUser(user.getId());
    				if (userRestService.addPegawai(user, pegawai).block().getStatus()=="200") {
    					return "success";
    				}
    			}
    		} 
    	}
        return "adduserwebservice-success";
    }
    
  /*
    @RequestMapping(value = "/profil", method = RequestMethod.GET)
    public String viewUser(Authentication authentication, Model model){

        List<RoleModel> listRole = roleService.findAll();
        UserModel user = userService.getUser(authentication.getName());
        PegawaiDetail pegawai;

        if(user.getRole().getId().equals(2L)){
            pegawai = userRestService.getPegawai(user.getId()).block();
            model.addAttribute("pegawai", pegawai);
            model.addAttribute("sisivitas", pegawai.getNama());
        }

        model.addAttribute("user", user);
        return "view-user-profile";
    }*/
}
