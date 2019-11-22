package apap.tugas.situ.rest;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
//import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class JumlahPegawaiResultDetail {

    String username = "";
    String role = "";

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "JumlahPegawaiResultDetail{" +
                "username='" + username + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}


