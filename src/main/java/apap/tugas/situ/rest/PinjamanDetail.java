package apap.tugas.situ.rest;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PinjamanDetail {

    @Id
    @NotNull
    @Size(max=32, min=32)
    private String id;

    @NotNull
    @Size(max=200)
    private String tanggalPengajuan;

    @NotNull
    @Size(max=200)
    private String tanggalDisetujui;

    @NotNull
    @Size(max=200)
    private String tanggalPengembalian;

    @NotNull
    private int jumlahPinjaman;

    @NotNull
    private int jumlahPengembalian;

    @NotNull
    private int status;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTanggalPengajuan() {
        return tanggalPengajuan;
    }

    public void setTanggalPengajuan(String tanggalPengajuan) {
        this.tanggalPengajuan = tanggalPengajuan;
    }

    public String getTanggalDisetujui() {
        return tanggalDisetujui;
    }

    public void setTanggalDisetujui(String tanggalDisetujui) {
        this.tanggalDisetujui = tanggalDisetujui;
    }

    public String getTanggalPengembalian() {
        return tanggalPengembalian;
    }

    public void setTanggalPengembalian(String tanggalPengembalian) {
        this.tanggalPengembalian = tanggalPengembalian;
    }

    public int getJumlahPinjaman() {
        return jumlahPinjaman;
    }

    public void setJumlahPinjaman(int jumlahPinjaman) {
        this.jumlahPinjaman = jumlahPinjaman;
    }

    public int getJumlahPengembalian() {
        return jumlahPengembalian;
    }

    public void setJumlahPengembalian(int jumlahPengembalian) {
        this.jumlahPengembalian = jumlahPengembalian;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "PinjamanDetail{" +
                "id='" + id + '\'' +
                ", tanggalPengajuan='" + tanggalPengajuan + '\'' +
                ", tanggalDisetujui='" + tanggalDisetujui + '\'' +
                ", tanggalPengembalian='" + tanggalPengembalian + '\'' +
                ", jumlahPinjaman=" + jumlahPinjaman +
                ", jumlahPengembalian=" + jumlahPengembalian +
                ", status=" + status +
                '}';
    }
}
