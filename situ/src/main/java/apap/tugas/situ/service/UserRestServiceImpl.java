package apap.tugas.situ.service;


import apap.tugas.situ.model.UserModel;

import apap.tugas.situ.rest.GuruDetail;
import apap.tugas.situ.rest.GuruDetailResponse;

import apap.tugas.situ.rest.BaseResponse;

import apap.tugas.situ.rest.PegawaiDetail;
import apap.tugas.situ.rest.PegawaiDetailResponse;
import apap.tugas.situ.rest.Setting;
import apap.tugas.situ.rest.SiswaDetail;
import apap.tugas.situ.rest.SiswaDetailResponse;

import org.json.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UserDetails;

import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
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
    	 DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
    	 String strDate = dateFormat.format(pegawai.getTanggalLahir());
    	 Map<String, String> data = new HashMap<>();
    	 data.put("idUser", user.getId());
    	 data.put("nip", pegawai.getNip());
    	 data.put("nama", pegawai.getNama());
    	 data.put("tempatLahir", pegawai.getTempatLahir());
    	 data.put("tanggalLahir", strDate);
    	 data.put("alamat", pegawai.getAlamat());
    	 data.put("telepon", pegawai.getTelepon());
    	 
    	
    	 return this.webClient.post().uri("/employees")
    			 .contentType(MediaType.APPLICATION_JSON)
                 .syncBody(data)
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
    	 DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
    	 String strDate = dateFormat.format(siswa.getTanggalLahir());
    	 Map<String, String> data = new HashMap<>();
    	 data.put("idUser", user.getId());
    	 data.put("nis", siswa.getNis());
    	 data.put("nama", siswa.getNama());
    	 data.put("tempatLahir", siswa.getTempatLahir());
    	 data.put("tanggalLahir", strDate);
    	 data.put("alamat", siswa.getAlamat());
    	 data.put("telepon", siswa.getTelepon());
    	 
    	
    	 return this.webClient.post().uri("/students")
    			 .contentType(MediaType.APPLICATION_JSON)
                 .syncBody(data)
                 .retrieve()
                 .bodyToMono(SiswaDetailResponse.class);
    }

    
    @Override
    public Mono<GuruDetailResponse> addGuru(UserModel user, GuruDetail guru) {
    	 DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
    	 String strDate = dateFormat.format(guru.getTanggalLahir());
    	 Map<String, String> data = new HashMap<>();
    	 data.put("idUser", user.getId());
    	 data.put("nig", guru.getNig());
    	 data.put("nama", guru.getNama());
    	 data.put("tempatLahir", guru.getTempatLahir());
    	 data.put("tanggalLahir", strDate);
    	 data.put("alamat", guru.getAlamat());
    	 data.put("telepon", guru.getTelepon());
    	 
    	
    	 return this.webClient.post().uri("/teachers")
    			 .contentType(MediaType.APPLICATION_JSON)
                 .syncBody(data)
                 .retrieve()
                 .bodyToMono(GuruDetailResponse.class);
    }



}
