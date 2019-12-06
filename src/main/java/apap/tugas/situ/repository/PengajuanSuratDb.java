package apap.tugas.situ.repository;

import apap.tugas.situ.model.PengajuanSuratModel;
import apap.tugas.situ.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

import java.util.Optional;

public interface PengajuanSuratDb extends JpaRepository<PengajuanSuratModel, Long> {
    Optional<PengajuanSuratModel> findById(Long id);
    List<PengajuanSuratModel> findAllByNomorSurat(String nomorSurat);
    List<PengajuanSuratModel> findAllByUser(UserModel user);
    List<PengajuanSuratModel> findAllByStatus(Integer status);
}
