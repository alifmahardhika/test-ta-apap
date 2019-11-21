package apap.tugas.situ.service;

import apap.tugas.situ.rest.JumlahPegawaiDetail;
import apap.tugas.situ.rest.JumlahPegawaiDetailResponse;
import apap.tugas.situ.rest.Setting;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
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
    public JumlahPegawaiDetailResponse getJumlahPegawaiDetail() { //Mono<JumlahPegawaiDetail>
//        System.out.println("====================masuk getjumlahpegawaidetail di lowonganrestserviceimpl");
        String jsonStr = getJsonStringJumlahPegawaiDetail();

        Gson gson = new Gson();
        JsonParser parser = new JsonParser();
        JsonObject object = (JsonObject) parser.parse(jsonStr);

        JumlahPegawaiDetailResponse jumlahPegawaiDetailResponse = gson.fromJson(object, JumlahPegawaiDetailResponse.class);
//        System.out.println("=====================bangsat");
//        System.out.println(jumlahPegawaiDetail.getResult());
//        System.out.println(jumlahPegawaiDetail.getMessages());
//        System.out.println(jumlahPegawaiDetail.getStatus());
//        System.out.println("======================object to string");
//        System.out.println(jumlahPegawaiDetail.toString());
//        System.out.println("================togetint");
        return jumlahPegawaiDetailResponse;
//        return this.webClient.get().uri("/")
//                .retrieve().bodyToMono(JumlahPegawaiDetail.class);
    }

    public String getJsonStringJumlahPegawaiDetail(){
        String result1 = this.webClient.get().uri("/")
                .retrieve().bodyToMono(String.class).block();
//        System.out.println("dari json: " + result1);
        return result1;
    }
//    public Boolean checkJumlahPustakawan(in)

    @Override
    public int getIntJumlah(JumlahPegawaiDetailResponse response){
//        String[] listResponse
//        System.out.println(response.getResult().toString());
////        System.out.println();
        return response.getResult().getJumlah();
    }


}
