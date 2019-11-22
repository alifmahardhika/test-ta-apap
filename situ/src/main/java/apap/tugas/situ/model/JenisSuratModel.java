package apap.tugas.situ.model;

import java.io.Serializable;
import java.util.*;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import apap.tugas.situ.model.*;

@Entity
@Table(name="jenis_surat")
public class JenisSuratModel implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idJenisSurat;

	@NotNull
	@Size(max = 200)
	@Column(name="nama", nullable = false)
	private String nama;

	@NotNull
	@Size(max = 200)
	@Column(name="keterangan", nullable = false)
	private String keterangan;

	public Long getIdJenisSurat() {
		return idJenisSurat;
	}

//	@OneToMany(mappedBy = "id_jenis_surat", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//	private List <PengajuanSuratModel> listPengajuanSurat;
//
//	public List<PengajuanSuratModel> getListPengajuanSurat() {
//		return listPengajuanSurat;
//	}
//
//	public void setListPengajuanSurat(List<PengajuanSuratModel> listPengajuanSurat) {
//		this.listPengajuanSurat = listPengajuanSurat;
//	}

	public void setIdJenisSurat(Long idJenisSurat) {
		this.idJenisSurat = idJenisSurat;
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