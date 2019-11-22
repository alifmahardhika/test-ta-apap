package apap.tugas.situ.rest;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PengajuanSuratDetailResponse {
    @JsonProperty("status")
    private String status;

    @JsonProperty("message")
    private String message;

    @JsonProperty("result")
    private PegawaiDetail result;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public PegawaiDetail getResult() {
        return result;
    }

    public void setResult(PegawaiDetail result) {
        this.result = result;
    }
}
