package apap.tugas.situ.service;

import apap.tugas.situ.rest.JumlahPegawaiDetail;
import apap.tugas.situ.rest.JumlahPegawaiDetailResponse;
import reactor.core.publisher.Mono;

//nanti bikin for loop dari response, sementara cuma terima jumlah
public interface LowonganRestService {
    public JumlahPegawaiDetailResponse getJumlahPegawaiDetail();//sesuain param kalau perlu yea //Mono<JumlahPegawaiDetail>
    public int getIntJumlah(JumlahPegawaiDetailResponse response);
}

