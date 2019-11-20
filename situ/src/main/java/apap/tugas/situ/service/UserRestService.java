package apap.tugas.situ.service;

import apap.tugas.situ.rest.PegawaiDetail;
import reactor.core.publisher.Mono;

public interface UserRestService {
    public Mono<PegawaiDetail> getPegawai(String uuid);
}
