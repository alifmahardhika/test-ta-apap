package apap.tugas.situ.restcontroller;

import apap.tugas.situ.model.Pinjaman;
import apap.tugas.situ.model.UserModel;
import apap.tugas.situ.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.Column;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Arrays;

@RestController
@RequestMapping("/api")
public class PinjamanRestController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private UserService userService;

    @PostMapping(value = "/buat-pinjaman")
    private Pinjaman createPinjaman(Authentication authentication, @Valid Pinjaman pinjaman, BindingResult bindingResult){
        System.out.println("===============disini");
        if (bindingResult.hasFieldErrors()){
            System.out.println("=======================================");
            System.out.println(pinjaman.toString());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Request Body has invalid type or missing field");
        }
        else{
//            HttpHeaders headers = new HttpHeaders();
//            headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
//            HttpEntity<Pinjaman> entity = new HttpEntity<Pinjaman>(pinjaman,headers);
//
//            UserModel user = userService.getUser(authentication.getName());
//
//            pinjaman.setIdUser(user.getId());
//            System.out.println("==========================================");
//            System.out.println(pinjaman.toString());
//            return restTemplate.exchange("https://b0e8cbdc-ae36-4281-85c3-be57c1219010.mock.pstmn.io/api/v1/pinjaman/tambah", HttpMethod.POST, entity, String.class).getBody();

            UserModel user = userService.getUser(authentication.getName());
//
            pinjaman.setIdUser(user.getId());
            System.out.println("********************************************");
            System.out.println(pinjaman.getJumlahPinjaman());
//            UserModel user =  userService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
////            pinjaman.setUserId(user.getId());

            HttpHeaders headers = new HttpHeaders();
            headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
            HttpEntity<Pinjaman> entity = new HttpEntity<Pinjaman>(pinjaman,headers);
            String res = restTemplate.exchange("https://b0e8cbdc-ae36-4281-85c3-be57c1219010.mock.pstmn.io/api/v1/pinjaman/tambah", HttpMethod.POST, entity, String.class).getBody();

            System.out.println("1234567890-234567890-345678904567890-4567890");
            System.out.println(res);
            return pinjaman;
        }
    }
}
