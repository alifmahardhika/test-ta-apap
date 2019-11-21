package apap.tugas.situ.rest;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class JumlahPegawaiDetailResponse {
    @JsonProperty("status")
    private String status;

    @JsonProperty("messages")
    private String messages;

    @JsonProperty("result")
    private JumlahPegawaiDetail result;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessages() {
        return messages;
    }

    public void setMessages(String message) {
        this.messages = message;
    }

    public JumlahPegawaiDetail getResult() {
        return result;
    }

    public void setResult(JumlahPegawaiDetail result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "JumlahPegawaiDetailResponse{" +
                "status='" + status + '\'' +
                ", message='" + messages + '\'' +
                ", result=" + result +
                '}';
    }
}
