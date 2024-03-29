package apap.tugas.situ.service;

import apap.tugas.situ.model.PengajuanSuratModel;
import apap.tugas.situ.model.UserModel;

import java.util.List;

public interface PengajuanSuratService {
    void addPengajuanSurat(PengajuanSuratModel pengajuanSuratModel);

    List<PengajuanSuratModel> getPengajuanSuratList();

    void deletePengajuanSurat(PengajuanSuratModel pengajuanSuratModel);

    List<PengajuanSuratModel> getPengajuanSuratListByUser(UserModel user);

}
