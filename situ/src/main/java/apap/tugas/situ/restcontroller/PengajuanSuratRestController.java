package apap.tugas.situ.restcontroller;

import apap.tugas.situ.model.PengajuanSuratModel;
import apap.tugas.situ.model.UserModel;
import apap.tugas.situ.rest.BaseResponse;
import apap.tugas.situ.rest.PengajuanSuratDetail;
import apap.tugas.situ.service.*;
import apap.tugas.situ.rest.BaseResponse;
import apap.tugas.situ.service.JenisSuratRestService;
import apap.tugas.situ.service.PengajuanSuratRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api")
public class PengajuanSuratRestController {
    @Autowired
    private PengajuanSuratRestService psrService;

    @Autowired
    private JenisSuratRestService jsrService;

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @GetMapping("/pengajuan-surat/{nomorSurat}")
    private BaseResponse<List<PengajuanSuratModel>> retrievePengajuanSurat(@PathVariable("nomorSurat") String nomorSurat) {
        BaseResponse<List<PengajuanSuratModel>> response = new BaseResponse<>();
        List<PengajuanSuratModel> data = psrService.getPengajuanSuratbyNomorSurat(nomorSurat);

        try{
            response.setStatus(200);
            response.setMessage("success");
            response.setResult(data);

            return response;
        }
        catch (NoSuchElementException e){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Surat dengan nomor surat "+ nomorSurat + " tidak ditemukan"
            );
        }
    }

    @PostMapping(value = "/pengajuan-surat/add/{username}")
    private BaseResponse<PengajuanSuratModel> addPengajuanSurat(@PathVariable(value = "username") String username, @RequestBody PengajuanSuratDetail surat, BindingResult bindingResult) {
        BaseResponse<PengajuanSuratModel> response = new BaseResponse<>();
        if (bindingResult.hasFieldErrors()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Request body has invalid type or missing field"
            );
        } else {
            UserModel user = new UserModel();
            PengajuanSuratModel pengajuanSurat = new PengajuanSuratModel();

            Long idJenisSurat = surat.getIdJenisSurat();
            String keterangan = surat.getKeterangan();

            if (userService.getUser(username) == null) {
                user.setPassword("");
                user.setUsername(username);
                user.setRole(roleService.getRoleById(5L));
                user.setListPengajuanSurat(new ArrayList<>());
                userService.addUser(user);
            } else {
                user = userService.getUser(username);
            }

            pengajuanSurat.setJenisSurat(jsrService.getJenisSuratById(idJenisSurat));
            pengajuanSurat.setKeterangan(keterangan);
            pengajuanSurat.setUser(user);
            psrService.createPengajuanSurat(pengajuanSurat);

            response.setMessage("success");
            response.setStatus(200);
            response.setResult(pengajuanSurat);

            return response;
        }
    }
}
