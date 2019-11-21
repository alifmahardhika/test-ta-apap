package apap.tugas.situ.service;

import apap.tugas.situ.model.PengajuanSuratModel;
import apap.tugas.situ.repository.PengajuanSuratDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional
public class PengajuanSuratRestServiceImpl implements PengajuanSuratRestService {
    @Autowired
    private PengajuanSuratDb pengajuanSuratDb;

    @Override
    public PengajuanSuratModel createPengajuanSurat(PengajuanSuratModel pengajuanSurat) {
        return pengajuanSuratDb.save(pengajuanSurat);
    }

    @Override
    public List<PengajuanSuratModel> getPengajuanSuratbyNomorSurat(String nomorSurat) {
        List<PengajuanSuratModel> pengajuanSuratList = pengajuanSuratDb.findAllByNomorSurat(nomorSurat);
        if (pengajuanSuratList.size() > 0) {
            return pengajuanSuratList;
        } else {
            throw new NoSuchElementException();
        }
    }
}
