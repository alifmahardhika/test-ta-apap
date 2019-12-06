package apap.tugas.situ.service;

import apap.tugas.situ.model.PengajuanSuratModel;
import apap.tugas.situ.model.UserModel;
import apap.tugas.situ.repository.PengajuanSuratDb;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class PengajuanSuratServiceImpl implements PengajuanSuratService {
    @Autowired
    PengajuanSuratDb psdb;

    @Override
    public PengajuanSuratModel getPengajuanSuratById(Long idPengajuanSurat) {
        Optional<PengajuanSuratModel> pengajuanSuratModel = psdb.findById(idPengajuanSurat);
        if (pengajuanSuratModel.isPresent()){
            return pengajuanSuratModel.get();
        } else {
            throw new NoSuchElementException();
        }
    }

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

    @Override
    public PengajuanSuratModel changeStatusPengajuanSurat(Long id, Integer status) {
        PengajuanSuratModel targetPS = getPengajuanSuratById(id);
        String nomorSuratBaru = "";
        List<PengajuanSuratModel> pengajuanSuratList = getPengajuanSuratList();

        targetPS.setStatus(status);
        if (status == 2) {
            targetPS.setTanggalDisetujui(LocalDate.now());

            String generatedString = RandomStringUtils.randomNumeric(8);
            String disetujui = LocalDate.now().toString().replaceAll("-", "");

            if (pengajuanSuratList.size() > 0) {
                for (PengajuanSuratModel ps : pengajuanSuratList) {
                    if ((ps.getNomorSurat().substring(8)).equals(generatedString))
                        generatedString = RandomStringUtils.randomNumeric(8);
                }
            }
            nomorSuratBaru += generatedString;
            nomorSuratBaru += disetujui;

            targetPS.setNomorSurat(nomorSuratBaru);
        }

        return targetPS;
    }
}
