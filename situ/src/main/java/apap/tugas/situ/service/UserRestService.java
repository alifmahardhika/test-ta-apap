package apap.tugas.situ.service;


import apap.tugas.situ.model.UserModel;
import apap.tugas.situ.rest.GuruDetail;
import apap.tugas.situ.rest.GuruDetailResponse;

import apap.tugas.situ.rest.BaseResponse;

import apap.tugas.situ.rest.PegawaiDetail;
import apap.tugas.situ.rest.PegawaiDetailResponse;
import apap.tugas.situ.rest.SiswaDetail;
import apap.tugas.situ.rest.SiswaDetailResponse;
import reactor.core.publisher.Mono;

import java.util.Map;

public interface UserRestService {

    
    public String generateKodeNIP(String tanggalLahir, String uuid); 
    public Mono<PegawaiDetailResponse> addPegawai(UserModel user, PegawaiDetail pegawai);
    public String generateKodeNIG(String tanggalLahir, String uuid);
    public String generateKodeNIS(String tanggalLahir, String uuid); 
    public Mono<SiswaDetailResponse> addSiswa(UserModel user, SiswaDetail siswa);
    public Mono<GuruDetailResponse> addGuru(UserModel user, GuruDetail guru);

    public Map<String, Object> getAllUsers(String roles);

    public Map<String, String> getUser(String uuid, String roles);

}
