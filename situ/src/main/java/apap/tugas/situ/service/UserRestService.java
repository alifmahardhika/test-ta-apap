package apap.tugas.situ.service;

import apap.tugas.situ.rest.BaseResponse;
import apap.tugas.situ.rest.PegawaiDetail;
import reactor.core.publisher.Mono;

import java.util.Map;

public interface UserRestService {
//    public Mono<PegawaiDetail> getPegawai(String uuid);
    public Map<String,String> getPegawai(String uuid);
}
