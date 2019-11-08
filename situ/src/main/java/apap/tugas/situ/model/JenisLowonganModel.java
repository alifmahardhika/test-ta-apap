package apap.tugas.situ.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(name="jenisLowongan")
public class JenisLowonganModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idJenisLowongan;

    @NotNull
    @Size(max = 200)
    @Column(name = "nama", nullable = false)
    private String nama;

    @Size(max = 200)
    @Column(name = "keterangan", nullable = false)
    private String keterangan;

    //Setter and getter

    public long getIdJenis() {
        return idJenisLowongan;
    }

    public void setIdJenis(long idJenisLowongan) {
        this.idJenisLowongan = idJenisLowongan;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }
}
