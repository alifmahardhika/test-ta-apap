package apap.tugas.situ.service;

import apap.tugas.situ.rest.JumlahPegawaiDetail;
import apap.tugas.situ.rest.JumlahPegawaiDetailResponse;
import apap.tugas.situ.rest.JumlahPegawaiResultDetail;
import apap.tugas.situ.rest.Setting;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class LowonganRestServiceImpl implements LowonganRestService{
    private final WebClient webClient;

    public LowonganRestServiceImpl(WebClient.Builder webClientBuilder){
        this.webClient = webClientBuilder.baseUrl(Setting.perpustakaanUrl).build();
    }

    @Override
    public JumlahPegawaiDetailResponse getJumlahPegawaiDetail() { //Mono<JumlahPegawaiDetail>
//        System.out.println("====================masuk getjumlahpegawaidetail di lowonganrestserviceimpl");
        String jsonStr = getJsonStringJumlahPegawaiDetail();

        Gson gson = new Gson();
        JsonParser parser = new JsonParser();
        JsonObject object = (JsonObject) parser.parse(jsonStr);

        JumlahPegawaiDetailResponse jumlahPegawaiDetailResponse = gson.fromJson(object, JumlahPegawaiDetailResponse.class);


        return jumlahPegawaiDetailResponse;
    }

    public String getJsonStringJumlahPegawaiDetail(){
        String result1 = this.webClient.get().uri("/api/user/list")
                .retrieve().bodyToMono(String.class).block();
        System.out.println("dari json: " + result1);
        return result1;
    }
//    public Boolean checkJumlahPustakawan(in)

    @Override
    public int getIntJumlah(JumlahPegawaiDetailResponse response){
        List<JumlahPegawaiResultDetail> results = response.getResult();
        int jumlah = 0;
        for (JumlahPegawaiResultDetail target: results){
            if (target.getRole().equalsIgnoreCase("Pustakawan")){
                jumlah++;
            }
        }
        return jumlah;
    }


}
