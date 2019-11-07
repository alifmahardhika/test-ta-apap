package apap.tugas.situ.repository;


import apap.tugas.situ.model.JenisLowonganModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JenisLowonganDb extends JpaRepository<JenisLowonganModel, Long> {
    List<JenisLowonganModel> findAll();
    JenisLowonganModel getJenisLowonganByIdJenisLowongan(Long idJenisLowongan);

}
