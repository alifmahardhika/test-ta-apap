package apap.tugas.situ.rest;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class JumlahPegawaiDetailResponse {
    @JsonProperty("status")
    private String status;

    @JsonProperty("message")
    private String message;

    @JsonProperty("result")
    private List<JumlahPegawaiResultDetail> result;

//    private JumlahPegawaiDetail result;

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

    public List<JumlahPegawaiResultDetail> getResult() {
        return result;
    }

    public void setResult(List<JumlahPegawaiResultDetail> results) {
        this.result = results;
    }

    @Override
    public String toString() {
        return "JumlahPegawaiDetailResponse{" +
                "status='" + status + '\'' +
                ", messages='" + message + '\'' +
                ", results=" + result.toString() +
                '}';
    }

    //    public JumlahPegawaiDetail getResult() {
//        return result;
//    }
//
//    public void setResult(JumlahPegawaiDetail result) {
//        this.result = result;
//    }

//    @Override
//    public String toString() {
//        return "JumlahPegawaiDetailResponse{" +
//                "status='" + status + '\'' +
//                ", message='" + messages + '\'' +
//                ", result=" + result +
//                '}';
//    }
}
