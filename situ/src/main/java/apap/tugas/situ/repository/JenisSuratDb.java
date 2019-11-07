package apap.tugas.situ.repository;

import apap.tugas.situ.model.JenisSuratModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

@Repository
public interface JenisSuratDb extends JpaRepository<JenisSuratModel, Long> {
    Optional<JenisSuratModel> findByIdJenisSurat(Long idJenisSurat);
	JenisSuratModel findByNama(String nama);
    List<JenisSuratModel> findAllByOrderByNamaAsc();
}


