package apap.tugas.situ.rest;

import apap.tugas.situ.model.JenisSuratModel;
import com.fasterxml.jackson.annotation.JsonProperty;

public class PengajuanSuratDetail {
    private String status;

    @JsonProperty
    private Long idJenisSurat;

    @JsonProperty
    private String keterangan;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getIdJenisSurat() {
        return idJenisSurat;
    }

    public void setIdJenisSurat(Long idJenisSurat) {
        this.idJenisSurat = idJenisSurat;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }
}
