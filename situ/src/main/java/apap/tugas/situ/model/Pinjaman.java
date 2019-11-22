package apap.tugas.situ.model;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public  class Pinjaman {

    //attribute
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate tanggalPengajuan;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate tanggalDisetujui;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate tanggalPengembalian;

    Integer jumlahPinjaman;

    Integer jumlahPengembalian;

    Integer status;

    String userId;

    //SETTER GETTER


    public LocalDate getTanggalPengajuan() {
        return tanggalPengajuan;
    }

    public void setTanggalPengajuan(LocalDate tanggalPengajuan) {
        this.tanggalPengajuan = tanggalPengajuan;
    }

    public LocalDate getTanggalDisetujui() {
        return tanggalDisetujui;
    }

    public void setTanggalDisetujui(LocalDate tanggalDisetujui) {
        this.tanggalDisetujui = tanggalDisetujui;
    }

    public LocalDate getTanggalPengembalian() {
        return tanggalPengembalian;
    }

    public void setTanggalPengembalian(LocalDate tanggalPengembalian) {
        this.tanggalPengembalian = tanggalPengembalian;
    }

    public Integer getJumlahPinjaman() {
        return jumlahPinjaman;
    }

    public void setJumlahPinjaman(Integer jumlahPinjaman) {
        this.jumlahPinjaman = jumlahPinjaman;
    }

    public Integer getJumlahPengembalian() {
        return jumlahPengembalian;
    }

    public void setJumlahPengembalian(Integer jumlahPengembalian) {
        this.jumlahPengembalian = jumlahPengembalian;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    //constructor
    public Pinjaman() {
        this.tanggalDisetujui = null;
        this.tanggalPengajuan = null;
        this.tanggalPengembalian = null;
        this.jumlahPengembalian = 0;
        this.jumlahPinjaman = 0;
        this.status = 0;
        this.userId = "";
    }

//    @Override
//    public String toString() {
//        return "Pinjaman{" +
//                "tanggalPengajuan=" + tanggalPengajuan +
//                ", tanggalDisetujui=" + tanggalDisetujui +
//                ", tanggalPengembalian=" + tanggalPengembalian +
//                ", jumlahPinjaman=" + jumlahPinjaman +
//                ", jumlahPengembalian=" + jumlahPengembalian +
//                ", status='" + status + '\'' +
//                ", userId='" + userId + '\'' +
//                '}';
//    }
}
