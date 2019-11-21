package apap.tugas.situ.rest;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class JumlahPegawaiResultDetail {
    @JsonProperty("Result")
    private List<JumlahPegawaiDetail> result;

    public List<JumlahPegawaiDetail> getResult() {
        return result;
    }

    public void setResult(List<JumlahPegawaiDetail> result) {
        this.result = result;
    }
}
