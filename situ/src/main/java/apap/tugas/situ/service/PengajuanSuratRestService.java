package apap.tugas.situ.service;

import apap.tugas.situ.model.PengajuanSuratModel;

import java.util.List;

public interface PengajuanSuratRestService {
    PengajuanSuratModel createPengajuanSurat(PengajuanSuratModel pengajuanSurat);

    List<PengajuanSuratModel> getPengajuanSuratbyNomorSurat(String nomorSurat);
}
