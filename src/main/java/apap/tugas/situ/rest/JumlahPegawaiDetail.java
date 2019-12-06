package apap.tugas.situ.rest;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
//import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class JumlahPegawaiDetail {

    private List<JumlahPegawaiResultDetail> results;

    public List<JumlahPegawaiResultDetail> getResults() {
        return results;
    }

    public void setResults(List<JumlahPegawaiResultDetail> results) {
        this.results = results;
    }
}


