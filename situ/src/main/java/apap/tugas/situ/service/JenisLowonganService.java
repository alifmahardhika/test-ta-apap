package apap.tugas.situ.service;


import apap.tugas.situ.model.JenisLowonganModel;

import java.util.List;

public interface JenisLowonganService {
    void addJenisLowongan(JenisLowonganModel jenisLowonganModel);
    List<JenisLowonganModel> findAllJenis();
    JenisLowonganModel findJenisById(Long idJenisLowongan);
    Boolean checkValid(String namaJenis);
    void deleteJenis(JenisLowonganModel jenisLowonganModel);
    Boolean checkDeletable(JenisLowonganModel jenisLowongan);
    long findIdByNama(String nama);

}
