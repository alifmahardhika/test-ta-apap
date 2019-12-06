package apap.tugas.situ.model;


import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name="lowongan")
public class LowonganModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idLowongan;

    @NotNull
    @Size(max = 200)
    @Column(name = "judul", nullable = false)
    private String judul;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "tanggalDibuka", nullable = false)
    private LocalDate tanggalDibuka;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "tanggalDitutup", nullable = false)
    private LocalDate tanggalDitutup;

    @NotNull
    @Size(max = 200)
    @Column(name = "keterangan", nullable = false)
    private String keterangan;

    @NotNull
    @Column(name = "jumlah", nullable = false)
    private Integer jumlah;

    //foreign key many to one ke jenis lowongan
    @NotNull
    @Column(name = "idJenis", nullable = false)
    private Long idJenis;

    //forign key many to one ke user
    //karena belum implement user maka di comment dulu
//    @NotNull
//    @Size(max = 200)
//    @Column(name = "uuidUser", nullable = false)
//    private String uuidUser;

    //setter and getter


    public long getIdLowongan() {
        return idLowongan;
    }

    public void setIdLowongan(long idLowongan) {
        this.idLowongan = idLowongan;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public LocalDate getTanggalDibuka() {
        return tanggalDibuka;
    }

    public void setTanggalDibuka(LocalDate tanggalDibuka) {
        this.tanggalDibuka = tanggalDibuka;
    }

    public LocalDate getTanggalDitutup() {
        return tanggalDitutup;
    }

    public void setTanggalDitutup(LocalDate tanggalDitutup) {
        this.tanggalDitutup = tanggalDitutup;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public Integer getJumlah() {
        return jumlah;
    }

    public void setJumlah(Integer jumlah) {
        this.jumlah = jumlah;
    }

    public Long getIdJenis() {
        return idJenis;
    }

    public void setIdJenis(Long idJenisLowongan) {
        this.idJenis = idJenisLowongan;
    }

//    public String getUuidUser() {
//        return uuidUser;
//    }
//
//    public void setUuidUser(String uuidUser) {
//        this.uuidUser = uuidUser;
//    }


    @Override
    public String toString() {
        return "LowonganModel{" +
                "judul='" + judul + '\'' +
                ", tanggalDibuka=" + tanggalDibuka +
                ", tanggalDitutup=" + tanggalDitutup +
                ", keterangan='" + keterangan + '\'' +
                ", jumlah=" + jumlah +
                ", idJenis=" + idJenis +
                '}';
    }

    public LowonganModel(@NotNull @Size(max = 200) String judul, @NotNull LocalDate tanggalDibuka, @NotNull LocalDate tanggalDitutup, @NotNull @Size(max = 200) String keterangan, @NotNull Integer jumlah, @NotNull Long idJenis) {
        this.judul = judul;
        this.tanggalDibuka = tanggalDibuka;
        this.tanggalDitutup = tanggalDitutup;
        this.keterangan = keterangan;
        this.jumlah = jumlah;
        this.idJenis = idJenis;
    }

    public LowonganModel() {
    }
}
