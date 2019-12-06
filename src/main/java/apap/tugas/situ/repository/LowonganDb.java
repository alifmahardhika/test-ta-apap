package apap.tugas.situ.repository;

import apap.tugas.situ.model.LowonganModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LowonganDb extends JpaRepository<LowonganModel, Long> {
    List<LowonganModel> findAll();
    LowonganModel getLowonganByIdLowongan(Long idLowongan);
}
