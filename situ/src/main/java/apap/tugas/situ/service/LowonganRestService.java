package apap.tugas.situ.service;

import apap.tugas.situ.rest.JumlahPegawaiDetail;
import reactor.core.publisher.Mono;

//nanti bikin for loop dari response, sementara cuma terima jumlah
public interface LowonganRestService {
    public Mono<JumlahPegawaiDetail> getJumlahPegawaiDetail();//sesuain param kalau perlu yea
}

