package apap.tugas.situ.rest;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
//import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class JumlahPegawaiDetail {

    private int jumlah;
//    @JsonIgnoreProperties("idUser")
//    private Long idUser;


    public int getJumlah() {
        return jumlah;
    }

    public void setJumlah(int jumlah) {
        this.jumlah = jumlah;
    }

    @Override
    public String toString() {
        return "JumlahPegawaiDetail{" +
                "jumlah=" + jumlah +
                '}';
    }
}


