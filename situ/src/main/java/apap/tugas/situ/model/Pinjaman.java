package apap.tugas.situ.model;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public  class Pinjaman {

    //attribute
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate tanggalPengajuan;

    Integer jumlahPinjaman;

    String idUser;

    //SETTER GETTER


    public LocalDate getTanggalPengajuan() {
        return tanggalPengajuan;
    }

    public void setTanggalPengajuan(LocalDate tanggalPengajuan) {
        this.tanggalPengajuan = tanggalPengajuan;
    }

    public Integer getJumlahPinjaman() {
        return jumlahPinjaman;
    }

    public void setJumlahPinjaman(Integer jumlahPinjaman) {
        this.jumlahPinjaman = jumlahPinjaman;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    //constructor

    public Pinjaman() {
        this.tanggalPengajuan = null;
        this.jumlahPinjaman = 0;
        this.idUser = "";
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
