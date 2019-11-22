package apap.tugas.situ.controller;


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
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRestService userRestService;

    @Autowired
    RoleService roleService;


//    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
//    private String addUserSubmit(@ModelAttribute UserModel user){
//        userService.addUser(user);
//        return "home";
//    }

//        @RequestMapping(value = "/admin", method = RequestMethod.POST)
//    private String halamanAdmin(@ModelAttribute UserModel user, Model model){
//        if(user.getRole().getRole() == "ADMIN"){
//            System.out.println("masuk ke if pertama");
//            return "admin";
//        }
//        else {
//                model.addAttribute("alasan", "Error! Tidak bisa mengakses halaman admin!");
//                return "error";
//            }
//    }

//    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
//    private String addUserSubmit(@ModelAttribute UserModel user, Model model) {
//        if (user.getPassword().length() < 8) {
//            model.addAttribute("pesan", "Password tidak boleh kurang dari 8 karakter!");
//            return "admin";
//        } else {
//            if (user.getPassword().matches(".*[a-zA-Z].*") && user.getPassword().matches(".*[0-9].*")) {
//                userService.addUser(user);
//                model.addAttribute("pesan", "Berhasil menambahkan user!");
//                return "admin";
//            } else {
//                model.addAttribute("pesan", "Password harus memiliki angka dan huruf");
//                return "admin";
//            }
//        }
//    }

//    @RequestMapping(value = "/update-password/{username}", method = RequestMethod.GET)
//    private String updatePassword(@PathVariable(value="username") String username, Model model) {
//        UserModel user = userService.getUser(username);
//        model.addAttribute("user", user);
//        model.addAttribute("username", username);
//        model.addAttribute("pesan", "");
//        return "form-update-password";
//    }
//
//    @RequestMapping(value = "/update-password/{username}", method = RequestMethod.POST)
//    private String updatePasswordSubmit(@PathVariable(value="username") String username, String oldpass, String newpass, String newconfirmedpass, Model model) {
//        UserModel user = userService.getUser(username);
//
//        if (newpass.matches(".*[a-zA-Z].*") && newpass.matches(".*[0-9].*") && newpass.length() >= 8) {
//            if (newpass.equals(newconfirmedpass) == false) {
//                model.addAttribute("pesan", "Konfirmasi password tidak sama!");
//                return "form-update-password";
//            } else if (newpass.equals(oldpass)){
//                model.addAttribute("pesan", "Password lama dan password baru sama!");
//                return "form-update-password";
//            }else {
//                boolean valid = userService.validatePassword(user.getPassword(), oldpass);
//                if (valid == true) {
//                    userService.updatePassword(username, newpass);
//                    return "update-password-success";
//                } else {
//                    model.addAttribute("pesan", "Password kurang sesuai!");
//                    return "form-update-password";
//                }
//            }
//        } else {
//            model.addAttribute("pesan", "Password tidak boleh kurang dari 8 karakter dan harus mengandung huruf dan angka!");
//            return "form-update-password";
//        }
//
//    }


    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public String addUserSubmit(Authentication authentication, @ModelAttribute UserModel user,
                                @RequestParam(required=false) String nama,
                                @RequestParam(required=false) String tempatLahir,
                                @RequestParam(required=false) String tanggalLahir,
                                @RequestParam(required=false) String alamat,
                                @RequestParam(required=false) String telepon, Model model) throws ParseException, java.text.ParseException {

        if (userService.getUser(user.getUsername()) != null) {
          List<RoleModel> listRole = roleService.findAll();
          UserModel userLogged = userService.getUser(authentication.getName());
          model.addAttribute("listRole", listRole);
          model.addAttribute("user", userLogged);
          model.addAttribute("errormsg", "Username tidak valid");
          return "admin";
//             return "error";
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
        model.addAttribute("username", user.getUsername());

        return "add-user-success";
    }

    @RequestMapping(value = "/profil", method = RequestMethod.GET)
    public String viewUser(Model model){

        List<RoleModel> listRole = roleService.findAll();
        UserModel user = userService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
        String userId = user.getId();
        Map<String, String> profil = null;
        String role = "";

        if(user.getRole().getId().equals(2L)){
            role = "employees";

        } else if (user.getRole().getId().equals(3L)){
            role = "teachers";

        } else if (user.getRole().getId().equals(4L)){
            role = "students";
        }

        Map<String, Object> allProfile = userRestService.getAllUsers(role);
        ArrayList listUser = (ArrayList) allProfile.get("result");
        ArrayList<String> listUuid = new ArrayList<String>();
        for (int i = 0; i <listUser.size(); i++ ){
            LinkedHashMap<String, Object> ab = (LinkedHashMap<String, Object>) listUser.get(i);
            listUuid.add((String)ab.get("idUser"));
        }
        if(listUuid.contains(userId)){
            profil = userRestService.getUser(userId, role);
            model.addAttribute("sisivitas", "Ada");
            System.out.println(listUuid);
            System.out.println(profil);
        }else {
            profil = null;
        }

        model.addAttribute("profil", profil);
        model.addAttribute("user", user);
        model.addAttribute("role", role);
        return "view-user-profile";
    }


}
