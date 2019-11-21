package apap.tugas.situ.rest;

import apap.tugas.situ.model.JenisSuratModel;
import com.fasterxml.jackson.annotation.JsonProperty;

public class PengajuanSuratDetail {
    private String status;

    @JsonProperty
    private Long id_jenis_surat;

    @JsonProperty
    private String keterangan;
}
