package apap.tugas.situ.service;

import apap.tugas.situ.model.PengajuanSuratModel;
import apap.tugas.situ.model.UserModel;
import apap.tugas.situ.repository.PengajuanSuratDb;
import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class PengajuanSuratServiceImpl implements PengajuanSuratService {
    @Autowired
    PengajuanSuratDb psdb;

    @Override
    public void addPengajuanSurat(PengajuanSuratModel pengajuanSuratModel) {
        pengajuanSuratModel.setNomorSurat("0");
        pengajuanSuratModel.setTanggalPengajuan(LocalDate.now());
        pengajuanSuratModel.setTanggalDisetujui(null);
        pengajuanSuratModel.setStatus(0);

        psdb.save(pengajuanSuratModel);
    }

    @Override
    public List<PengajuanSuratModel> getPengajuanSuratList() {
        return psdb.findAll();
    }

    @Override
    public void deletePengajuanSurat(PengajuanSuratModel pengajuanSuratModel) {
        psdb.delete(pengajuanSuratModel);
    }

    @Override
    public List<PengajuanSuratModel> getPengajuanSuratListByUser(UserModel user) {
        return psdb.findAllByUser(user);
    }
}
