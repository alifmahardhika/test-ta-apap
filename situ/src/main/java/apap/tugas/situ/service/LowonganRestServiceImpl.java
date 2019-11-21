package apap.tugas.situ.service;

import apap.tugas.situ.rest.JumlahPegawaiDetail;
import apap.tugas.situ.rest.Setting;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class LowonganRestServiceImpl implements LowonganRestService{
    private final WebClient webClient;

    public LowonganRestServiceImpl(WebClient.Builder webClientBuilder){
        this.webClient = webClientBuilder.baseUrl(Setting.jumlahPegawaiUrl).build();
    }

    @Override
    public Mono<JumlahPegawaiDetail> getJumlahPegawaiDetail() {
        System.out.println("====================masuk getjumlahpegawaidetail di lowonganrestserviceimpl");
        String result1 = this.webClient.get().uri("/")
                .retrieve().bodyToMono(String.class).block();
        System.out.println("dari json: " + result1);
        return this.webClient.get().uri("/")
                .retrieve().bodyToMono(JumlahPegawaiDetail.class);
    }
//    public Boolean checkJumlahPustakawan(in)
}
