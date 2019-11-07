package apap.tugas.situ.service;

import apap.tugas.situ.model.LowonganModel;

import java.util.List;

public interface LowonganService {
    void addLowongan(LowonganModel lowonganModel);
    List<LowonganModel> findAllLowongan();
    LowonganModel findLowonganById(Long idLowongan);
}
