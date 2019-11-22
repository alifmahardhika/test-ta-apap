package apap.tugas.situ.restcontroller;

import apap.tugas.situ.model.PengajuanSuratModel;
import apap.tugas.situ.service.JenisSuratRestService;
import apap.tugas.situ.service.PengajuanSuratRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api")
public class PengajuanSuratRestController {
    @Autowired
    private PengajuanSuratRestService psrService;

    @Autowired
    private JenisSuratRestService jsrService;

    @GetMapping("/pengajuan-surat/{nomorSurat}")
    private List<PengajuanSuratModel> retrievePengajuanSurat(@PathVariable("nomorSurat") String nomorSurat) {
        try {
            return psrService.getPengajuanSuratbyNomorSurat(nomorSurat);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "ID Restoran " + nomorSurat + " Not Found"
            );
        }
    }

    @PostMapping(value = "/pengajuan-surat")
    private PengajuanSuratModel addPengajuanSurat(@Valid @RequestBody PengajuanSuratModel pengajuanSurat, BindingResult bindingResult) {
        if (bindingResult.hasFieldErrors()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Request body has invalid type or missing field"
            );
        } else {
            Long idJenisSurat = pengajuanSurat.getJenisSurat().getIdJenisSurat();
            pengajuanSurat.setJenisSurat(jsrService.getJenisSuratById(idJenisSurat));
            System.out.println(pengajuanSurat);
            return psrService.createPengajuanSurat(pengajuanSurat);
        }
    }
}
