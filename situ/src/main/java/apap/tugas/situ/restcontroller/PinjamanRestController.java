package apap.tugas.situ.restcontroller;

import apap.tugas.situ.model.Pinjaman;
import apap.tugas.situ.model.UserModel;
import apap.tugas.situ.rest.PinjamanResponseDetail;
import apap.tugas.situ.service.UserService;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
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
import org.springframework.web.servlet.ModelAndView;

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
    private ModelAndView createPinjaman(Authentication authentication, @Valid Pinjaman pinjaman, BindingResult bindingResult, Model model){
        if (bindingResult.hasFieldErrors()){
            System.out.println(pinjaman.toString());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Request Body has invalid type or missing field");
        }
        else{

            UserModel user = userService.getUser(authentication.getName());

            pinjaman.setIdUser(user.getId());
            System.out.println(pinjaman.getJumlahPinjaman());


            HttpHeaders headers = new HttpHeaders();
            headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
            HttpEntity<Pinjaman> entity = new HttpEntity<Pinjaman>(pinjaman,headers);
            String res = restTemplate.exchange("https://b0e8cbdc-ae36-4281-85c3-be57c1219010.mock.pstmn.io/api/v1/pinjaman/tambah", HttpMethod.POST, entity, String.class).getBody();


            Gson gson = new Gson();
            JsonParser parser = new JsonParser();
            JsonObject object = (JsonObject) parser.parse(res);

            PinjamanResponseDetail response = gson.fromJson(object, PinjamanResponseDetail.class);
            System.out.println(res);

            System.out.println(response.getResult().getJumlahPinjaman());

            if (response.getStatus().equals("200")){//berhasil

                model.addAttribute("jumlahPinjaman", response.getResult().getJumlahPinjaman());
                ModelAndView modelAndView = new ModelAndView();
                modelAndView.setViewName("webservice-pinjaman-berhasil");
                return modelAndView;
            }
            else{//gagal
                model.addAttribute("jumlahPinjaman", response.getResult().getJumlahPinjaman());
                ModelAndView modelAndView = new ModelAndView();
                modelAndView.setViewName("webservice-pinjaman-gagal");
                return modelAndView;

            }
        }
    }
}