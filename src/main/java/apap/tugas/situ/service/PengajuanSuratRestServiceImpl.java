package apap.tugas.situ.service;

import apap.tugas.situ.model.PengajuanSuratModel;
import apap.tugas.situ.repository.PengajuanSuratDb;
import apap.tugas.situ.rest.Setting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional
public class PengajuanSuratRestServiceImpl implements PengajuanSuratRestService {
    private final WebClient webClient;

    @Autowired
    private PengajuanSuratDb pengajuanSuratDb;

    public PengajuanSuratRestServiceImpl(WebClient.Builder webClientBuilder){
        this.webClient = webClientBuilder.baseUrl(Setting.pengajuanSuratUrl).build();
    }

    @Override
    public PengajuanSuratModel createPengajuanSurat(PengajuanSuratModel pengajuanSurat) {
        pengajuanSurat.setNomorSurat("0");
        pengajuanSurat.setTanggalPengajuan(LocalDate.now());
        pengajuanSurat.setTanggalDisetujui(null);
        pengajuanSurat.setStatus(0);

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
