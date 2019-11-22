package apap.tugas.situ.service;


import apap.tugas.situ.model.UserModel;
import apap.tugas.situ.rest.*;

import org.json.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UserDetails;

import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Map;
import java.util.Random;
@Service
public class UserRestServiceImpl implements UserRestService {

    private final WebClient webClient;

    @Override
    public String generateKodeNIP(String tanggalLahir, String uuid) {
        String[] date = tanggalLahir.split("-");
        return "P".concat(date[2]).concat(date[1]).concat(date[0])
                .concat(String.valueOf(generateRandomChar()))
                .concat(String.valueOf(generateRandomChar()))
                .concat(String.valueOf(generateRandomNum()))
                .concat(String.valueOf(generateRandomNum()))
                .concat(String.valueOf(generateRandomNum()))
                .concat(uuid);
    }
    

    
    @Override
    public String generateKodeNIS(String tanggalLahir, String uuid) {
        String[] date = tanggalLahir.split("-");
        return "S".concat(date[2]).concat(date[1]).concat(date[0])
                .concat(String.valueOf(generateRandomChar()))
                .concat(String.valueOf(generateRandomChar()))
                .concat(String.valueOf(generateRandomNum()))
                .concat(String.valueOf(generateRandomNum()))
                .concat(String.valueOf(generateRandomNum()))
                .concat(uuid);
    }
    
    @Override
    public String generateKodeNIG(String tanggalLahir, String uuid) {
        String[] date = tanggalLahir.split("-");
        return "G".concat(date[2]).concat(date[1]).concat(date[0])
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
    public Mono<PegawaiDetailResponse> addPegawai(UserModel user, PegawaiDetail pegawai) {
    	 JSONObject data = new JSONObject();
    	 data.put("idUser", user.getId());
    	 data.put("nip", pegawai.getNip());
    	 data.put("nama", pegawai.getNama());
    	 data.put("tempatLahir", pegawai.getTempatLahir());
    	 data.put("tanggalLahir", pegawai.getTanggalLahir());
    	 data.put("alamat", pegawai.getAlamat());
    	 data.put("telepon", pegawai.getTelepon());
    	 
    	 System.out.println(user.getId() + "\n" + pegawai.getNip() + "\n" + pegawai.getNama() + "\n" +
    	 pegawai.getTempatLahir() + "\n" + pegawai.getAlamat() + "\n" +pegawai.getTelepon());
    	 
    	 return this.webClient.post().uri("/employees")
    			 .contentType(MediaType.APPLICATION_JSON)
                 .syncBody(data.toString())
                 .retrieve()
                 .bodyToMono(PegawaiDetailResponse.class);
    }
    
    @Override
    public Map<String, Object> getAllUsers(String roles) {
        try {
            return this.webClient.get().uri(Setting.sivitasUrl + "/" + roles)
                    .retrieve().bodyToMono(Map.class).block();
        }
        catch (NullPointerException e){
            return null;
        }
    }

    @Override
    public Map<String, String> getUser(String uuid, String roles) {
        try {
            return this.webClient.get().uri(Setting.sivitasUrl + "/" + roles + "/" + uuid)
                    .retrieve().bodyToMono(Map.class).block();
        }
        catch (NullPointerException e){
            return null;
        }
    }



    @Override
    public Mono<SiswaDetailResponse> addSiswa(UserModel user, SiswaDetail siswa) {
    	// MultiValueMap<String, String> data = new LinkedMultiValueMap<>();
    	 JSONObject data = new JSONObject();
    	 data.put("idUser", user.getId());
    	 data.put("nama", siswa.getNama());
    	 data.put("tanggalLahir", siswa.getTanggalLahir());
    	 data.put("tempatLahir", siswa.getTempatLahir());
    	 data.put("alamat", siswa.getAlamat());
    	 data.put("telepon", siswa.getTelepon());
    	 data.put("nip", siswa.getNis());
    	 return this.webClient.post().uri("/students")
    			 .contentType(MediaType.APPLICATION_JSON)
                 .syncBody(data.toString())
                 .retrieve()
                 .bodyToMono(SiswaDetailResponse.class);
    }

    public Mono<GuruDetailResponse> addGuru(UserModel user, GuruDetail guru) {
	   	 MultiValueMap<String, String> data = new LinkedMultiValueMap<>();
	   	 data.add("idUser", user.getId());
	   	 data.add("nama", guru.getNama());
	   	 data.add("tanggalLahir", guru.getTanggalLahir());
	   	 data.add("tempatLahir", guru.getTempatLahir());
	   	 data.add("alamat", guru.getAlamat());
	   	 data.add("telepon", guru.getTelepon());
	   	 data.add("nip", guru.getNig());
	   	 return this.webClient.post().uri("/teachers")
	   			 .contentType(MediaType.APPLICATION_JSON)
	                .syncBody(data.toString())
	                .retrieve()
	                .bodyToMono(GuruDetailResponse.class);
   }


}
