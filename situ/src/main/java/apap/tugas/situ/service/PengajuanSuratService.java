package apap.tugas.situ.service;

import apap.tugas.situ.model.PengajuanSuratModel;

import java.util.List;

public interface PengajuanSuratService {
    void addPengajuanSurat(PengajuanSuratModel pengajuanSuratModel);

    List<PengajuanSuratModel> getPengajuanSuratList();

    void deletePengajuanSurat(PengajuanSuratModel pengajuanSuratModel);

}
