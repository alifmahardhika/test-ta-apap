package apap.tugas.situ.service;

import apap.tugas.situ.rest.PegawaiDetail;
import apap.tugas.situ.rest.Setting;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import javax.transaction.Transactional;
import java.util.Random;
@Service
public class UserRestServiceImpl implements UserRestService {

    private final WebClient webClient;


    private String generateKodeNIP(String tanggalLahir, String uuid) {
        String[] date = tanggalLahir.split("-");
        return "P".concat(date[2]).concat(date[1]).concat(date[0])
                .concat(String.valueOf(generateRandomChar()))
                .concat(String.valueOf(generateRandomChar()))
                .concat(String.valueOf(generateRandomNum()))
                .concat(String.valueOf(generateRandomNum()))
                .concat(String.valueOf(generateRandomNum()))
                .concat(uuid);
    }



    private char generateRandomChar(){
        Random random = new Random();
        char text = (char) (Math.floorMod(random.nextInt(), 26) + 'A');
        return text;
    }

    private int generateRandomNum(){
        Random random = new Random();
        return Math.floorMod(random.nextInt(), 10);
    }
    public UserRestServiceImpl(WebClient.Builder webClientBuilder){
        this.webClient = webClientBuilder.baseUrl(Setting.sivitasUrl).build();
    }

    @Override
    public Mono<PegawaiDetail> getPegawai(String uuid) {
        return this.webClient.get().uri("/employees/" + uuid)
                .retrieve().bodyToMono(PegawaiDetail.class);

    }

}
