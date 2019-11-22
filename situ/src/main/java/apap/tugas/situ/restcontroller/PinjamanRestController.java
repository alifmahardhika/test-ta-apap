package apap.tugas.situ.restcontroller;

import apap.tugas.situ.model.Pinjaman;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.Column;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@RestController
@RequestMapping("/api")
public class PinjamanRestController {

    @PostMapping(value = "/buat-pinjaman")
    private Pinjaman createPinjaman(@Valid @RequestBody Pinjaman pinjaman, BindingResult bindingResult){
        if (bindingResult.hasFieldErrors()){
            System.out.println("=======================================");
            System.out.println(pinjaman.toString());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Request Body has invalid type or missing field");
        }
        else{
            System.out.println("********************************************");
            System.out.println(pinjaman.getJumlahPinjaman());
            return pinjaman;
        }
    }
}
