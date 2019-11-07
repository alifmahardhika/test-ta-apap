package apap.tugas.situ.model;


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
    @Column(name = "tanggalDibuka", nullable = false)
    private LocalDate tanggalDibuka;

    @NotNull
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
    @Column(name = "idJenisLowongan", nullable = false)
    private Long idJenisLowongan;

    //forign key many to one ke user
    @NotNull
    @Size(max = 200)
    @Column(name = "uuidUser", nullable = false)
    private String uuidUser;

    //setter and getter


    public long getIdRestoran() {
        return idRestoran;
    }

    public void setIdRestoran(long idRestoran) {
        this.idRestoran = idRestoran;
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

    public Long getIdJenisLowongan() {
        return idJenisLowongan;
    }

    public void setIdJenisLowongan(Long idJenisLowongan) {
        this.idJenisLowongan = idJenisLowongan;
    }

    public String getUuidUser() {
        return uuidUser;
    }

    public void setUuidUser(String uuidUser) {
        this.uuidUser = uuidUser;
    }
}
